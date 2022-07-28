package com.toni.margicalmusic.domain.usecases

import com.google.common.truth.Truth
import com.toni.margicalmusic.TestUtiDispatchers
import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.ResponseState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetHomePageDataUseCaseTest {

    private lateinit var genreSongRepository: GenreRepository
    private lateinit var artistsRepository: ArtistsRepository
    private lateinit var songsRepository: SongsRepository
    private lateinit var appDispatchers: AppDispatchers
    private lateinit var getHomePageDataUseCase: GetHomePageDataUseCase

    @Before
    fun setUp() {
        genreSongRepository = mockk()
        artistsRepository = mockk()
        songsRepository = mockk()
        appDispatchers = TestUtiDispatchers()

        getHomePageDataUseCase = GetHomePageDataUseCase(
            genreSongRepository, artistsRepository, songsRepository, appDispatchers
        )
    }

    @Test
    fun `should return a triple with response states for artists, songs and genres`() = runTest {
        // given
        val artist = Artist(id = 0, name = "", songCount = 0, albumCount = 0, image = null)
        val song = Song(
            id = 0,
            albumId = 0,
            artistId = 0,
            title = null,
            artistName = null,
            albumName = null,
            duration = 0,
            trackNumber = 0,
            trackImage = null,
            mbid = null
        )
        val genre = GenreSongModel(
            name = "", song = Song(
                id = 0,
                albumId = 0,
                artistId = 0,
                title = null,
                artistName = null,
                albumName = null,
                duration = 0,
                trackNumber = 0,
                trackImage = null,
                mbid = null
            ), count = 0
        )
        // when
        coEvery { artistsRepository.fetchArtists() } returns flowOf(
            ResponseState.Success(
                listOf(
                    artist
                )
            )
        )
        coEvery { genreSongRepository.fetchGenres() } returns flowOf(
            ResponseState.Success(
                listOf(
                    genre
                )
            )
        )

        coEvery { songsRepository.fetchSongs(10) } returns flowOf(
            ResponseState.Success(
                listOf(
                    song
                )
            )
        )

        // assert
        val response = getHomePageDataUseCase.invoke().first()

        Truth.assertThat(response).isInstanceOf(Triple::class.java)
        Truth.assertThat((response.first as ResponseState.Success).data).isEqualTo(listOf(artist))
        Truth.assertThat((response.second as ResponseState.Success).data).isEqualTo(listOf(song))
        Truth.assertThat((response.third as ResponseState.Success).data).isEqualTo(listOf(genre))
    }

}