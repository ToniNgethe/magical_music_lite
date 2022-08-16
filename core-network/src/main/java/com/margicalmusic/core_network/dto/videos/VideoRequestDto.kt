package com.toni.margicalmusic.data.dto.videos


import com.squareup.moshi.Json

data class VideoRequestDto(
    @Json(name = "artist")
    val artist: String?,
    @Json(name = "song")
    val song: String?
)