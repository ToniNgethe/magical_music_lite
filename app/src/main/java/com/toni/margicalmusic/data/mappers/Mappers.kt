package com.toni.margicalmusic.data.mappers

import com.margicalmusic.core_database.entity.SongsEntity
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video

fun SongsEntity.toLyricModel(): Lyric = Lyric(lyrics = this.lyrics)
fun SongsEntity.toVideoModel(): Video =
    Video(title = title, duration = null, artist = artistName, videoId = video)