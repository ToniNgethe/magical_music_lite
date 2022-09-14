package com.margicalmusic.core_network.services

import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.margicalmusic.core_network.data.dto.videos.VideoRequestDto
import com.margicalmusic.core_network.data.dto.videos.VideoResponseDto
import com.toni.margicalmusic.data.services.VideosService
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class VideosServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var videosService: VideosService

    private val okHttpClient = OkHttpClient.Builder().build()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        videosService = Retrofit.Builder().client(okHttpClient).baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()).asLenient())
            .build().create(VideosService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `correct response is mapped onto VideoResponseDto`() = runTest {
        // given
        val sampleResponse =
            "{\"status\":\"00\",\"message\":\"VideofoundforLastNightLonely\",\"data\":[{\"id\":\"5GzLsZJo4tw\",\"original_title\":\"JonPardi-LastNightLonely(OfficialMusicVideo)\",\"title\":\"LastNightLonely(OfficialMusicVideo)\",\"artist\":\"JonPardi\",\"duration\":185,\"publishedAt\":\"2022-06-01T02:37:25.636Z\"}]}".trimIndent()

        val result = MockResponse().setBody(sampleResponse).setResponseCode(200)
        mockWebServer.enqueue(result)

        // when
        val response = videosService.getVideo(VideoRequestDto(artist = "", song = ""))

        // assert
        Truth.assertThat(response).isEqualTo(
            VideoResponseDto(
                status = "00", message = "VideofoundforLastNightLonely", data = arrayListOf(
                    VideoResponseDto.Data(
                        id = "5GzLsZJo4tw",
                        originalTitle = "JonPardi-LastNightLonely(OfficialMusicVideo)",
                        title = "LastNightLonely(OfficialMusicVideo)",
                        artist = "JonPardi",
                        duration = 185,
                        publishedAt = "2022-06-01T02:37:25.636Z"
                    )
                )
            )
        )
    }

}