package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Lyric
import com.margicalmusic.core_network.ResponseState

interface LyricsRepository {
    suspend fun fetchLyrics(title: String, artistName: String): ResponseState<Lyric>
}