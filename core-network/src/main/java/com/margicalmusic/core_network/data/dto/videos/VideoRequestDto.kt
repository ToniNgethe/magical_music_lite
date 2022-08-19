package com.margicalmusic.core_network.data.dto.videos

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class VideoRequestDto(
    @SerialName("artist") val artist: String?, @SerialName("song") val song: String?
)