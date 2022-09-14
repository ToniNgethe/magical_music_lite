package com.toni.margicalmusic.domain.usecases

import com.google.common.truth.Truth
import com.margicalmusic.core_database.data.entity.SongsEntity
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.TestUtiDispatchers
import com.magicalmusic.feature_selected_song.domain.model.Lyric
import com.magicalmusic.feature_selected_song.domain.repository.LyricsRepository
import com.magicalmusic.feature_song.domain.SongsRepository
import com.magicalmusic.feature_selected_song.domain.repository.VideoRepository
import com.toni.margicalmusic.lyricModel
import com.toni.margicalmusic.songEntity
import com.toni.margicalmusic.video
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetLyricsAndVideoUseCaseTest {

    private lateinit var songsRepository: SongsRepository
    private lateinit var videoRepository: VideoRepository
    private lateinit var lyricsRepository: LyricsRepository
    private lateinit var appDispatchers: AppDispatchers

    private lateinit var getLyricsAndVideoUseCase: com.magicalmusic.feature_selected_song.domain.usecases.GetLyricsAndVideoUseCase

    @Before
    fun setUp() {
        songsRepository = mockk()
        videoRepository = mockk()
        lyricsRepository = mockk()
        appDispatchers = TestUtiDispatchers()

        getLyricsAndVideoUseCase =
            com.magicalmusic.feature_selected_song.domain.usecases.GetLyricsAndVideoUseCase(
                lyricsRepository = lyricsRepository,
                videoRepository = videoRepository,
                songsRepository = songsRepository,
                appDispatchers = appDispatchers
            )
    }


    @Test
    fun `should return a pair with lyrics and video state given the song is not cached`() =
        runTest {
            // given

            // when
            coEvery { songsRepository.getCachedSong(1) } returns ResponseState.Error(
                UiText.DynamicText(
                    "Song not cached"
                )
            )
            coEvery { lyricsRepository.fetchLyrics("", "") } returns ResponseState.Success(
                lyricModel
            )
            coEvery { videoRepository.getVideo("", "") } returns ResponseState.Success(video)
            coEvery {
                songsRepository.cacheSong(
                    SongsEntity(
                        songId = 1,
                        title = video.title,
                        video = video.videoId,
                        artistName = video.artist,
                        lyrics = lyricModel.lyrics
                    )
                )
            } returns Unit

            // assert
            val response = getLyricsAndVideoUseCase.invoke("", "", 1)

            Truth.assertThat(response).isInstanceOf(Pair::class.java)
            Truth.assertThat(response.first).isInstanceOf(ResponseState.Success::class.java)
            Truth.assertThat(response.second).isInstanceOf(ResponseState.Success::class.java)
            Truth.assertThat((response.first as ResponseState.Success).data).isEqualTo(lyricModel)
            Truth.assertThat((response.second as ResponseState.Success).data).isEqualTo(video)

            coVerify(exactly = 1) { lyricsRepository.fetchLyrics("", "") }
            coVerify(exactly = 1) { videoRepository.getVideo("", "") }
        }


    @Test
    fun `should return cached song given it exists locally`() = runTest {
        // given
        // when
        coEvery { songsRepository.getCachedSong(songEntity.songId) } returns ResponseState.Success(
            songEntity
        )
        // assert
        val response = getLyricsAndVideoUseCase.invoke("", "", 0)
        Truth.assertThat(response).isInstanceOf(Pair::class.java)
        Truth.assertThat(response.first).isInstanceOf(ResponseState.Success::class.java)
        Truth.assertThat(response.second).isInstanceOf(ResponseState.Success::class.java)

        Truth.assertThat((response.first as ResponseState.Success).data).isEqualTo(lyricModel)
        Truth.assertThat((response.second as ResponseState.Success).data).isEqualTo(video)

        coVerify(exactly = 1) { songsRepository.getCachedSong(0) }
    }

    @Test
    fun `should not save song whose get video or lyrics request fails`() = runTest {
        // given
        // when
        coEvery { songsRepository.getCachedSong(1) } returns ResponseState.Error(
            UiText.DynamicText(
                "Song not cached"
            )
        )
        coEvery { lyricsRepository.fetchLyrics("", "") } returns ResponseState.Error(
            UiText.DynamicText("Unable to fetch song lyrics")
        )
        coEvery { videoRepository.getVideo("", "") } returns ResponseState.Success(video)
        coEvery {
            songsRepository.cacheSong(
                SongsEntity(
                    songId = 1,
                    title = video.title,
                    video = video.videoId,
                    artistName = video.artist,
                    lyrics = lyricModel.lyrics
                )
            )
        } returns Unit

        // assert
        val response = getLyricsAndVideoUseCase.invoke("", "", 1)
        Truth.assertThat(response.first).isInstanceOf(ResponseState.Error::class.java)
        Truth.assertThat((response.first) as ResponseState.Error)
            .isEqualTo(ResponseState.Error<Lyric>(UiText.DynamicText("Unable to fetch song lyrics")))
        Truth.assertThat((response.second as ResponseState.Success).data).isEqualTo(video)
    }
}