package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun fetchArtists(): Flow<ResponseState<List<Artist>>>
}