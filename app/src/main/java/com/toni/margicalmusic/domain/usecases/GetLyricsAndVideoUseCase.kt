package com.toni.margicalmusic.domain.usecases

import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import com.toni.margicalmusic.domain.repositories.VideoRepository
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetLyricsAndVideoUseCase @Inject constructor(
    private val lyricsRepository: LyricsRepository, private val videoRepository: VideoRepository
) {
    suspend fun invoke(
        title: String, artistName: String
    ): Pair<ResponseState<Lyric>, ResponseState<Video>> {
        val lyrics = lyricsRepository.fetchLyrics(title = title, artistName)
        val videos = videoRepository.getVideo(title = title, artistName = artistName)
        return Pair(lyrics, videos)
    }
}