package com.toni.margicalmusic.domain.usecases

import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import com.margicalmusic.core_network.ResponseState
import javax.inject.Inject

class GetSongLyricsUseCase @Inject constructor(private val lyricsRepository: LyricsRepository) {
    suspend operator fun invoke(title: String, artistName: String): ResponseState<Lyric> =
        lyricsRepository.fetchLyrics(
            title = title, artistName = artistName
        )
}