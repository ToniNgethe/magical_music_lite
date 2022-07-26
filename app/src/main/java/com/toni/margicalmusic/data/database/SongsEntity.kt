package com.toni.margicalmusic.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongsEntity(
    @PrimaryKey val id: Int? = null,
    val songId: String?,
    val title: String?,
    val artistName: String?,
    val video: String?,
    val lyrics: String
)