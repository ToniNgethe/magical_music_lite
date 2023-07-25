package com.margicalmusic.core_media.models

import java.io.Serializable

data class Artist(
    val id: Long,
    val name: String,
    val songCount: Int,
    val albumCount: Int,
    val image: String? = null
) : Serializable