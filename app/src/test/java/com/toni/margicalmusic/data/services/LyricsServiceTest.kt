package com.toni.margicalmusic.data.services

import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.toni.margicalmusic.data.dto.lyrics.LyricsRequestDto
import com.toni.margicalmusic.data.dto.lyrics.LyricsResponseDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class LyricsServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var lyricsService: LyricsService
    private val client = OkHttpClient.Builder().build()
    private val successReponse =
        "{\"status\":\"00\",\"message\":\"LyricsforLastNightLonelyfound\",\"data\":\"lyrics\"}".trimIndent()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        lyricsService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")).client(client)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()).asLenient())
            .build().create(LyricsService::class.java)
    }


    @After
    fun shutDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `success response is mapped to LyricsResponseDto`() = runTest {
        // given
        val response = MockResponse().setBody(successReponse).setResponseCode(200)
        mockWebServer.enqueue(response)
        // when
        val result = lyricsService.getLyrics(LyricsRequestDto(artist = "", song = ""))

        // assert
        Truth.assertThat(result).isEqualTo(
            LyricsResponseDto(
                status = "00", message = "LyricsforLastNightLonelyfound", data = "lyrics"
            )
        )
    }
}