package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.data.database.SongsEntity
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun fetchSongs(limit: Int = 0): Flow<ResponseState<List<Song>>>
    suspend fun getCachedSong(songId: Int?): ResponseState<SongsEntity>
    suspend fun cacheSong( songsEntity: SongsEntity?  )
}