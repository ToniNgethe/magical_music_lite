package com.margicalmusic.core_network.data.dto.lyrics


import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LyricsResponseDto(
    @SerialName("data") val data: String?,
    @SerialName("message") val message: String?,
    @SerialName("status") val status: String?
)