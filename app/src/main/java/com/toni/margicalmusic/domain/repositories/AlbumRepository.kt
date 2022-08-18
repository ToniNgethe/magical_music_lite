package com.toni.margicalmusic.domain.repositories

import com.margicalmusic.core_media.models.Album
import com.margicalmusic.core_network.ResponseState
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun fetchLocalAlbums(): Flow<ResponseState<List<Album>>>
}