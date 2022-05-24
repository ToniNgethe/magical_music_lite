package com.toni.margicalmusic.domain.usecases

import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.MediaState
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
    suspend fun invoke(): Flow<Triple<MediaState<List<Artist>>, MediaState<List<Song>>, MediaState<List<GenreSongModel>>>> {
        val artists = artistsRepository.fetchArtists()
        val songs = songsRepository.fetchSongs()
        val genres = genreSongRepository.fetchGenres()

        val result = withContext(appDispatchers.io()) {
            async { combine(artists, songs, genres, ::Triple) }
        }.await()
        return result
    }
}