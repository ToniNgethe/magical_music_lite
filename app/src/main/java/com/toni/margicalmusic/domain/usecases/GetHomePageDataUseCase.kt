package com.toni.margicalmusic.domain.usecases

import com.margicalmusic.core_media.models.Artist
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.feature_artists.domain.ArtistsRepository
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.magicalmusic.songs.domain.SongsRepository
import com.margicalmusic.core_media.models.Song
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
    suspend fun invoke(): Flow<Triple<ResponseState<List<Artist>>, ResponseState<List<Song>>, ResponseState<List<GenreSongModel>>>> {
        val artists = artistsRepository.fetchArtists()
        val songs = songsRepository.fetchSongs( 10 )
        val genres = genreSongRepository.fetchGenres()

        val result = withContext(appDispatchers.io()) {
            async { combine(artists, songs, genres, ::Triple) }
        }.await()
        return result
    }
}