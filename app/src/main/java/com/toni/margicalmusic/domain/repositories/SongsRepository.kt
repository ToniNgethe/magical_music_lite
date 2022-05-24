package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.utils.MediaState
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun fetchSongs(): Flow<MediaState<List<Song>>>
}