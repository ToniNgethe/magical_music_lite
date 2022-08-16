package com.toni.margicalmusic.data.dto.lyrics


import com.squareup.moshi.Json


data class LyricsResponseDto(
    @field:Json(name = "data") val data: String?,
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "status") val status: String?
)