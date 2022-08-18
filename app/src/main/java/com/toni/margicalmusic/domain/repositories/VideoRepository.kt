package com.toni.margicalmusic.domain.repositories

import com.toni.margicalmusic.domain.models.Video
import com.margicalmusic.core_network.ResponseState

interface VideoRepository {
    suspend fun getVideo(title: String, artistName: String): ResponseState<Video>
}