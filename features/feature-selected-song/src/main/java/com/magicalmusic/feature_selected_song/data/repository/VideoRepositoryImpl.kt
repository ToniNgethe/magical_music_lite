package com.magicalmusic.feature_selected_song.data.repository

import com.magicalmusic.feature_selected_song.domain.model.Video
import com.magicalmusic.feature_selected_song.domain.repository.VideoRepository
import com.margicalmusic.core_network.ErrorHandler.parseRequestException
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_network.data.dto.videos.VideoRequestDto
import com.margicalmusic.core_utils.UiText
import com.magicalmusic.feature_selected_song.data.mappers.toVideoModel
import com.margicalmusic.core_network.services.VideosService
import timber.log.Timber
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(val service: VideosService) : VideoRepository {
    override suspend fun getVideo(title: String, artistName: String): ResponseState<Video> = try {
        val response = service.getVideo(
            VideoRequestDto(
                artist = artistName, song = title
            )
        )
        if (response.status == "00") {
            ResponseState.Success(response.videoData?.get(0)!!.toVideoModel())
        } else {
            ResponseState.Error(UiText.DynamicText(response.message!!))
        }
    } catch (e: Exception) {
        parseRequestException(e)
    }
}