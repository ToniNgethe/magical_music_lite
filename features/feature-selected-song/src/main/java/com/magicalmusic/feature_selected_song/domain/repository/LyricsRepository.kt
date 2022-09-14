package com.magicalmusic.feature_selected_song.domain.repository

import com.magicalmusic.feature_selected_song.domain.model.Lyric
import com.margicalmusic.core_network.ResponseState

interface LyricsRepository {
    suspend fun fetchLyrics(title: String, artistName: String): ResponseState<Lyric>
}