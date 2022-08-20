package com.margicalmusic.feature_artists.domain

import com.margicalmusic.core_media.models.Artist
import com.margicalmusic.core_network.ResponseState
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun fetchArtists(): Flow<ResponseState<List<Artist>>>
}