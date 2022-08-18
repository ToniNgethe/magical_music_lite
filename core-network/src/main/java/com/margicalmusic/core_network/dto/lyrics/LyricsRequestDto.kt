package com.margicalmusic.core_network.dto.lyrics


import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LyricsRequestDto(
    @SerialName("artist") val artist: String?, @SerialName("song") val song: String?
)