package com.toni.margicalmusic.data.dto.lyrics


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class LyricsRequestDto(
    @field:Json(name = "artist") val artist: String?, @field:Json(name = "song") val song: String?
)