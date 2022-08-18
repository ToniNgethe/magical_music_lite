package com.toni.margicalmusic.data.repository

import com.margicalmusic.core_media.dataloaders.ArtistLoader
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ArtistsRepositoryImpl @Inject constructor(
    val artistsLoader: ArtistLoader, val appDispatchers: AppDispatchers
) : ArtistsRepository {


    override fun fetchArtists() = flow {
        try {
            val artists = artistsLoader.getAllArtists()
            if (artists.isNotEmpty()) {
                emit(ResponseState.Success(artists))
            } else {
                emit(ResponseState.Error(UiText.StaticText(com.margicalmusic.resources.R.string.no_artists_found)))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(UiText.DynamicText(e.message!!)))
        }
    }.flowOn(appDispatchers.io())
}