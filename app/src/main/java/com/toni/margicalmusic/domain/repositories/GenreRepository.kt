package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun fetchGenres(): Flow<ResponseState<List<GenreSongModel>>>
}