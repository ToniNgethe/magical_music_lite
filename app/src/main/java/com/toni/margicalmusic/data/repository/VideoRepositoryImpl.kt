package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.data.dto.videos.VideoRequestDto
import com.toni.margicalmusic.data.services.VideosService
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.repositories.VideoRepository
import com.toni.margicalmusic.utils.ErrorHandler.parseRequestException
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
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