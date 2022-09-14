package com.magicalmusic.feature_selected_song.domain.repository

import com.magicalmusic.feature_selected_song.domain.model.Video
import com.margicalmusic.core_network.ResponseState

interface VideoRepository {
    suspend fun getVideo(title: String, artistName: String): ResponseState<Video>
}