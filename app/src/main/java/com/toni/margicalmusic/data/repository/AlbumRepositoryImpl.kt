package com.toni.margicalmusic.data.repository

import com.margicalmusic.core_media.dataloaders.AlbumLoader
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.domain.repositories.AlbumRepository
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
            else emit(ResponseState.Error(UiText.StaticText(com.margicalmusic.resources.R.string.no_albums)))
        } catch (e: Exception) {
            emit(ResponseState.Error(UiText.DynamicText(e.toString())))
        }
    }.flowOn(appDispatchers.io())

}