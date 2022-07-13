package com.toni.margicalmusic.data.dto.lyrics


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.toni.margicalmusic.domain.models.Lyric

data class LyricsResponseDto(
    @field:Json(name = "data") val data: String?,
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "status") val status: String?
) {
    fun toLyricModel(): Lyric = Lyric(lyrics = data!!)
}