package com.toni.margicalmusic.domain.usecases
import com.toni.margicalmusic.data.database.SongsEntity
import com.toni.margicalmusic.data.database.toLyricModel
import com.toni.margicalmusic.data.database.toVideoModel
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.domain.repositories.VideoRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.ResponseState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLyricsAndVideoUseCase @Inject constructor(
    private val lyricsRepository: LyricsRepository,
    private val videoRepository: VideoRepository,
    private val songsRepository: SongsRepository,
    private val appDispatchers: AppDispatchers
) {
    suspend fun invoke(
        title: String, artistName: String, songId: Long?
    ): Pair<ResponseState<Lyric>, ResponseState<Video>> {
        val songState = songsRepository.getCachedSong(songId = songId?.toInt())
        return if (songState is ResponseState.Success) {
            val lyric = ResponseState.Success(songState.data.toLyricModel())
            val video = ResponseState.Success(songState.data.toVideoModel())
            Pair(lyric, video)
        } else {
            val lyricsState = lyricsRepository.fetchLyrics(title = title, artistName)
            val videosState = videoRepository.getVideo(title = title, artistName = artistName)
            if (lyricsState is ResponseState.Success && videosState is ResponseState.Success) {
                cacheSongData(songId = songId, lyricsState, videosState)
            }
            Pair(lyricsState, videosState)
        }
    }

    private suspend fun cacheSongData(
        songId: Long?, lyricsState: ResponseState<Lyric>, videosState: ResponseState<Video>
    ) {
        withContext(appDispatchers.io()) {
            val entity = SongsEntity(songId = songId?.toInt(), "", "", "", "")
            entity.lyrics =   (lyricsState as ResponseState.Success).data.lyrics

            entity.apply {
                video = (videosState as ResponseState.Success).data.videoId
                title = videosState.data.title
                artistName = videosState.data.artist
            }
            songsRepository.cacheSong(entity)
        }
    }
}