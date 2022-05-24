package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.R
import com.toni.margicalmusic.data.dataloaders.GenreLoader
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.MediaState
import com.toni.margicalmusic.utils.UiText
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    val genreLoader: GenreLoader, val appDispatchers: AppDispatchers
) : GenreRepository {


    override fun fetchGenres() = flow {
        try {
            genreLoader.buildGenresLibrary()
            val genresModels = mutableListOf<GenreSongModel>()
            for ((key, value) in genreLoader.mGenresSongCountHashMap) {
                /* check if already exists */
                val model = GenreSongModel(key, genreLoader.getFirstSingInGenre(key), value)
                genresModels.add(model)
            }
            if (genresModels.isNotEmpty()) {
                emit(MediaState.Success(genresModels.toList()))
            } else {
                emit(MediaState.Error(UiText.StaticText(R.string.no_genres)))
            }
        } catch (e: Exception) {
            emit(MediaState.Error(UiText.DynamicText(e.message.toString())))
        }
    }.flowOn(appDispatchers.io())

}