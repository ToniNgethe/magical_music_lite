package com.toni.margicalmusic.data.repository

import com.google.common.truth.Truth
import com.toni.margicalmusic.TestUtiDispatchers
import com.toni.margicalmusic.data.dto.lyrics.LyricsRequestDto
import com.toni.margicalmusic.data.dto.lyrics.LyricsResponseDto
import com.toni.margicalmusic.data.services.LyricsService
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LyricsRepositoryImplTest {
    private lateinit var lyricsService: LyricsService
    private lateinit var appDispatchers: AppDispatchers

    private lateinit var lyricsRepository: LyricsRepository

    val lyricsRequestDto = LyricsRequestDto(
        artist = "", song = ""
    )

    @Before
    fun setUp() {
        lyricsService = mockk()
        appDispatchers = TestUtiDispatchers()

        lyricsRepository = LyricsRepositoryImpl(lyricsService, appDispatchers)
    }

    @Test
    fun `should return lyrics when get lyrics request is a success`() = runTest {
        // given
        val sampleLyric = Lyric(
            lyrics = "sample lyrics"
        )
        // when
        coEvery {
            lyricsService.getLyrics(
                lyricsRequestDto
            )
        } returns LyricsResponseDto(status = "00", message = "success", data = "sample lyrics")

        // assert
        val response = lyricsRepository.fetchLyrics(title = "", artistName = "")

        Truth.assertThat(response).isInstanceOf(ResponseState.Success::class.java)
        Truth.assertThat((response as ResponseState.Success).data).isEqualTo(sampleLyric)
    }

    @Test
    fun `should throw an exception when fetch lyrics request fails`() = runTest {
        // given

        // when
        coEvery {
            lyricsService.getLyrics(
                lyricsRequestDto
            )
        } throws Exception("unable to complete request")

        val response = lyricsRepository.fetchLyrics("", "")

        // assert
        Truth.assertThat(response).isInstanceOf(ResponseState.Error::class.java)
        Truth.assertThat((response as ResponseState.Error).uiText)
            .isEqualTo(UiText.DynamicText("unable to complete request"))
    }

}