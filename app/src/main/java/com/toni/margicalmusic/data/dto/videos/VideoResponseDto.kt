package com.toni.margicalmusic.data.dto.videos


import com.squareup.moshi.Json
import com.toni.margicalmusic.domain.models.Video

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
    ) {

        fun toVideoModel(): Video = Video(
            videoId = id, title = originalTitle, duration = duration, artist = artist
        )

    }
}