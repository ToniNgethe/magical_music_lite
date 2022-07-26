package com.toni.margicalmusic.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video

@Entity(tableName = "song")
data class SongsEntity(
    @PrimaryKey var songId: Int? = null,
    var title: String?,
    var artistName: String?,
    var video: String?,
    var lyrics: String,
)


fun SongsEntity.toLyricModel(): Lyric = Lyric(lyrics = this.lyrics)
fun SongsEntity.toVideoModel(): Video =
    Video(title = title, duration = null, artist = artistName, videoId = video)