package com.margicalmusic.core_network.data.dto.videos


import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class VideoResponseDto(
    @SerialName("data") val `data`: List<Data?>?,
    @SerialName("message") val message: String?,
    @SerialName("status") val status: String?
) {
    @kotlinx.serialization.Serializable
    data class Data(
        @SerialName("artist") val artist: String?,
        @SerialName("duration") val duration: Int?,
        @SerialName("id") val id: String?,
        @SerialName("original_title") val originalTitle: String?,
        @SerialName("publishedAt") val publishedAt: String?,
        @SerialName("title") val title: String?
    )
}