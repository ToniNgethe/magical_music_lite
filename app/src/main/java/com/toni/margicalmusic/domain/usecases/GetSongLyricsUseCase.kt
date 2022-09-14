package com.toni.margicalmusic.domain.usecases

import com.magicalmusic.feature_selected_song.domain.model.Lyric
import com.magicalmusic.feature_selected_song.domain.repository.LyricsRepository
import com.margicalmusic.core_network.ResponseState
import javax.inject.Inject

class GetSongLyricsUseCase @Inject constructor(private val lyricsRepository: LyricsRepository) {
    suspend operator fun invoke(title: String, artistName: String): ResponseState<Lyric> =
        lyricsRepository.fetchLyrics(
            title = title, artistName = artistName
        )
}