package com.toni.margicalmusic.data.services

import com.margicalmusic.core_network.data.dto.videos.VideoRequestDto
import com.margicalmusic.core_network.data.dto.videos.VideoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface VideosService {

    @POST("video")
    suspend fun getVideo(@Body requestDto: VideoRequestDto): VideoResponseDto
}