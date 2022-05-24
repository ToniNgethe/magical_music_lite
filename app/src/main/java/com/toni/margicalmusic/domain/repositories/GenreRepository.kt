package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.utils.MediaState
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun fetchGenres(): Flow<MediaState<List<GenreSongModel>>>
}