package com.toni.margicalmusic.domain.usecases

import com.google.common.truth.Truth
import com.toni.margicalmusic.TestUtiDispatchers
import com.toni.margicalmusic.artist
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.genre
import com.toni.margicalmusic.song
import com.toni.margicalmusic.utils.AppDispatchers
import com.margicalmusic.core_network.ResponseState
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