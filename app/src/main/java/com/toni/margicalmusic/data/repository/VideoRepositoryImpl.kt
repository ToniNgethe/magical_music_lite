package com.toni.margicalmusic.data.repository

import com.margicalmusic.core_network.ErrorHandler.parseRequestException
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_network.data.dto.videos.VideoRequestDto
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.data.mappers.toVideoModel
import com.toni.margicalmusic.data.services.VideosService
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.repositories.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(val service: VideosService) : VideoRepository {
    override suspend fun getVideo(title: String, artistName: String): ResponseState<Video> = try {
        val response = service.getVideo(
            VideoRequestDto(
                artist = artistName, song = title
            )
        )
        if (response.status == "00") {
            ResponseState.Success(response.data?.get(0)!!.toVideoModel())
        } else {
            ResponseState.Error(UiText.DynamicText(response.message!!))
        }
    } catch (e: Exception) {
        parseRequestException(e)
    }
}