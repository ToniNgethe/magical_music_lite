package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.utils.ResponseState

interface LyricsRepository {
    suspend fun fetchLyrics(title: String, artistName: String): ResponseState<Lyric>
}