package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.R
import com.toni.margicalmusic.data.dataloaders.SongLoader
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.MediaState
import com.toni.margicalmusic.utils.UiText
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(
    private val songLoader: SongLoader, private val appDispatchers: AppDispatchers
) : SongsRepository {

    override fun fetchSongs() = flow {
        try {
            val songs = songLoader.getAllSongs()
            if (songs.isNotEmpty()) {
                emit(MediaState.Success(songs))
            } else {
                emit(MediaState.Error(UiText.StaticText(R.string.no_songs)))
            }
        } catch (e: Exception) {
            emit(MediaState.Error(UiText.DynamicText(e.message!!)))
        }
    }.flowOn(appDispatchers.io())
}