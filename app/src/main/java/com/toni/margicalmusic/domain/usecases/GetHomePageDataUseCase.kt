package com.toni.margicalmusic.domain.usecases

import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomePageDataUseCase @Inject constructor(
    val genreSongRepository: GenreRepository,
    val artistsRepository: ArtistsRepository,
    val songsRepository: SongsRepository,
    val appDispatchers: AppDispatchers
) {
    suspend fun invoke(): Flow<Triple<ResponseState<List<com.margicalmusic.core_media.models.Artist>>, ResponseState<List<com.margicalmusic.core_media.models.Song>>, ResponseState<List<GenreSongModel>>>> {
        val artists = artistsRepository.fetchArtists()
        val songs = songsRepository.fetchSongs( 10 )
        val genres = genreSongRepository.fetchGenres()

        val result = withContext(appDispatchers.io()) {
            async { combine(artists, songs, genres, ::Triple) }
        }.await()
        return result
    }
}