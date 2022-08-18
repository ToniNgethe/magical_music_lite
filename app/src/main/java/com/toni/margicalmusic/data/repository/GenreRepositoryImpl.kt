package com.toni.margicalmusic.data.repository

import com.margicalmusic.core_media.dataloaders.GenreLoader
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.repositories.GenreRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreLoader: GenreLoader, val appDispatchers: AppDispatchers
) : GenreRepository {

    override fun fetchGenres() = flow {
        try {
            val genresModels = mutableListOf<GenreSongModel>()
            for ((key, value) in genreLoader.mGenresSongCountHashMap) {
                /* check if already exists */
                val model = GenreSongModel(key, genreLoader.getFirstSongInGenre(key), value)
                genresModels.add(model)
            }
            if (genresModels.isNotEmpty()) {
                emit(ResponseState.Success(genresModels.toList()))
            } else {
                emit(ResponseState.Error(UiText.StaticText(com.margicalmusic.resources.R.string.no_genres)))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(UiText.DynamicText(e.message.toString())))
        }
    }.flowOn(appDispatchers.io())

}