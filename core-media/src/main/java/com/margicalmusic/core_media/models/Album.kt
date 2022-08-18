package com.margicalmusic.core_media.models

import java.io.Serializable

data class Album(
    val id: Long = -1,
    val title: String = "",
    val artistName: String = "",
    val artistId: Long = -1,
    val songCount: Int = -1,
    val year: Int = -1,
    val view: Int = 0,
    val defaultImage: String = "",
    val mbid: String? = ""
) : Serializable