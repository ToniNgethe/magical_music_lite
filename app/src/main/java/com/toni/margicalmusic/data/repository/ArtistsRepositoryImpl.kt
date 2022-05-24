package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.R
import com.toni.margicalmusic.data.dataloaders.ArtistLoader
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.MediaState
import com.toni.margicalmusic.utils.UiText
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
                emit(MediaState.Success(artists))
            } else {
                emit(MediaState.Error(UiText.StaticText(R.string.no_artists_found)))
            }
        } catch (e: Exception) {
            emit(MediaState.Error(UiText.DynamicText(e.message!!)))
        }
    }.flowOn(appDispatchers.io())
}