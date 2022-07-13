package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.R
import com.toni.margicalmusic.data.dataloaders.AlbumLoader
import com.toni.margicalmusic.domain.repositories.AlbumRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumLoader: AlbumLoader, private val appDispatchers: AppDispatchers
) : AlbumRepository {

    override fun fetchLocalAlbums() = flow {
        try {
            val albums = albumLoader.getAllAlbums()
            if (albums.isNotEmpty()) emit(ResponseState.Success(albums))
            else emit(ResponseState.Error(UiText.StaticText(R.string.no_albums)))
        } catch (e: Exception) {
            emit(ResponseState.Error(UiText.DynamicText(e.toString())))
        }
    }.flowOn(appDispatchers.io())

}