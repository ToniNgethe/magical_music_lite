package com.magicalmusic.feature_song.domain

import com.margicalmusic.core_database.data.entity.SongsEntity
import com.margicalmusic.core_media.models.Song
import com.margicalmusic.core_network.ResponseState
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun fetchSongs(limit: Int = 0): Flow<ResponseState<List<Song>>>
    suspend fun getCachedSong(songId: Int?): ResponseState<SongsEntity>
    suspend fun cacheSong( songsEntity: SongsEntity?  )
}