package com.margicalmusic.core_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song")
data class SongsEntity(
    @PrimaryKey var songId: Int? = null,
    var title: String?,
    var artistName: String?,
    var video: String?,
    var lyrics: String,
)