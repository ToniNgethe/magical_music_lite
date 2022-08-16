package com.margicalmusic.core_network.dto.videos


import com.squareup.moshi.Json

data class VideoResponseDto(
    @field:Json(name = "data") val `data`: List<Data?>?,
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "status") val status: String?
) {
    data class Data(
        @field:Json(name = "artist") val artist: String?,
        @field:Json(name = "duration") val duration: Int?,
        @field:Json(name = "id") val id: String?,
        @field:Json(name = "original_title") val originalTitle: String?,
        @field:Json(name = "publishedAt") val publishedAt: String?,
        @field:Json(name = "title") val title: String?
    )
}