package com.toni.margicalmusic.data.repository

import com.google.common.truth.Truth
import com.margicalmusic.core_network.data.dto.videos.VideoRequestDto
import com.margicalmusic.core_network.data.dto.videos.VideoResponseDto
import com.toni.margicalmusic.data.services.VideosService
import com.magicalmusic.feature_selected_song.domain.repository.VideoRepository
import com.margicalmusic.core_network.ResponseState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VideoRepositoryImplTest {
    private lateinit var videoService: VideosService
    private lateinit var videoRepository: com.magicalmusic.feature_selected_song.domain.repository.VideoRepository

    @Before
    fun setUp() {
        videoService = mockk()
        videoRepository =
            com.magicalmusic.feature_selected_song.data.repository.VideoRepositoryImpl(videoService)
    }


    @Test
    fun `should return a success response state given request to get video is a success`() =
        runTest {
            // given
            val sampleVideoResponseDto = VideoResponseDto(
                status = "00", message = "success", data = listOf(
                    VideoResponseDto.Data(
                        artist = "artist name",
                        duration = 488,
                        id = "1",
                        originalTitle = "song title",
                        publishedAt = null,
                        title = "title"
                    )
                )
            )
            // when
            coEvery {
                videoService.getVideo(
                    VideoRequestDto(
                        "", ""
                    )
                )
            } returns sampleVideoResponseDto
            // assert
            val response = videoRepository.getVideo("", "")

            Truth.assertThat(response).isInstanceOf(ResponseState.Success::class.java)
        }

    @Test
    fun `should return a error response state given request to get video fails`() =
        runTest {
            // given
            // when
            coEvery {
                videoService.getVideo(
                    VideoRequestDto(
                        "", ""
                    )
                )
            } throws Exception("Unable to process your request at the moment")
            // assert
            val response = videoRepository.getVideo("", "")

            Truth.assertThat(response).isInstanceOf(ResponseState.Error::class.java)
        }
}