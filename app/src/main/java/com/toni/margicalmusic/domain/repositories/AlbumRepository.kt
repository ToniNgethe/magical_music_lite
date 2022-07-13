package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Album
import com.toni.margicalmusic.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun fetchLocalAlbums(): Flow<ResponseState<List<Album>>>
}