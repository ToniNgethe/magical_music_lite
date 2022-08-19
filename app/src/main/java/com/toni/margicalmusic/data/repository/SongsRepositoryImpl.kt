package com.toni.margicalmusic.data.repository

import com.margicalmusic.core_database.data.dao.SongsDao
import com.margicalmusic.core_database.data.entity.SongsEntity
import com.margicalmusic.core_media.dataloaders.SongLoader
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.repositories.SongsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(
    private val songLoader: SongLoader,
    private val appDispatchers: AppDispatchers,
    private val songDao: SongsDao
) : SongsRepository {

    override fun fetchSongs(limit: Int) = flow {
        try {
            val songs = songLoader.getAllSongs(limit = limit)
            if (songs.isNotEmpty()) {
                emit(ResponseState.Success(songs))
            } else {
                emit(ResponseState.Error(UiText.StaticText(com.margicalmusic.resources.R.string.no_songs)))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(UiText.DynamicText(e.message!!)))
        }
    }.flowOn(appDispatchers.io())

    override suspend fun getCachedSong(songId: Int?): ResponseState<SongsEntity> = try {
        val song = songDao.getSongById(songId = songId!!)
        if (song != null) ResponseState.Success(song)
        else ResponseState.Error(UiText.StaticText(com.margicalmusic.resources.R.string.no_cached_song))
    } catch (e: Exception) {
        ResponseState.Error(UiText.DynamicText(e.message ?: ""))
    }

    override suspend fun cacheSong(songsEntity: SongsEntity?) {
        songDao.insert(songsEntity)
    }

}