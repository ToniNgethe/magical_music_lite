package com.toni.margicalmusic.domain.usecases

import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import com.toni.margicalmusic.domain.repositories.VideoRepository
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
import javax.inject.Inject

class GetLyricsAndVideoUseCase @Inject constructor(
    private val lyricsRepository: LyricsRepository, private val videoRepository: VideoRepository
) {
    suspend fun invoke(
        title: String, artistName: String
    ): ResponseState<Pair<Lyric, Video>> = try {
        var lyricModel: Lyric? = null
        var videoModel: Video? = null

        val lyrics = lyricsRepository.fetchLyrics(title = title, artistName)
        if (lyrics is ResponseState.Error) {
            lyrics.uiText
        } else if (lyrics is ResponseState.Success) {
            lyricModel = lyrics.data
        }

        val videos = videoRepository.getVideo(title = title, artistName = artistName)
        if (videos is ResponseState.Error) {
            videos.uiText
        } else if (videos is ResponseState.Success) {
            videoModel = videos.data
        }
        ResponseState.Success(Pair(lyricModel!!, videoModel!!))
    } catch (e: Exception) {
        ResponseState.Error(UiText.DynamicText(e.message.toString()))
    }
}