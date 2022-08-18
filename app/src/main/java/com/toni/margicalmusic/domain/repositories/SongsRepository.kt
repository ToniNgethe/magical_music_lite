package com.toni.margicalmusic.domain.repositories

import com.margicalmusic.core_database.entity.SongsEntity
import com.margicalmusic.core_media.models.Song
import com.margicalmusic.core_network.ResponseState
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun fetchSongs(limit: Int = 0): Flow<ResponseState<List<com.margicalmusic.core_media.models.Song>>>
    suspend fun getCachedSong(songId: Int?): ResponseState<SongsEntity>
    suspend fun cacheSong( songsEntity: SongsEntity?  )
}