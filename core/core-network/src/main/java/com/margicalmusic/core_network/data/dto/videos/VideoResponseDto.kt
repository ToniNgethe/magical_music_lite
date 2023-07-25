package com.margicalmusic.core_network.data.dto.videos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponseDto(
    @SerialName("data") val videoData: List<VideoData?>?,
    @SerialName("message") val message: String?,
    @SerialName("status") val status: String?
) {
    @Serializable
    data class VideoData(
        @SerialName("artist") val artist: String?,
        @SerialName("duration") val duration: Int?,
        @SerialName("id") val id: String?,
        @SerialName("original_title") val originalTitle: String?,
        @SerialName("publishedAt") val publishedAt: String? = "",
        @SerialName("title") val title: String?
    )
}