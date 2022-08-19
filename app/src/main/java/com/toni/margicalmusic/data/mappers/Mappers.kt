package com.toni.margicalmusic.data.mappers

import com.margicalmusic.core_database.data.entity.SongsEntity
import com.margicalmusic.core_network.data.dto.videos.VideoResponseDto
import com.margicalmusic.core_network.data.dto.lyrics.LyricsResponseDto
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video

fun SongsEntity.toLyricModel(): Lyric = Lyric(lyrics = this.lyrics)
fun SongsEntity.toVideoModel(): Video =
    Video(title = title, duration = null, artist = artistName, videoId = video)

fun LyricsResponseDto.toLyricModel(): Lyric = Lyric(lyrics = data!!)
fun VideoResponseDto.Data.toVideoModel(): Video = Video(
    videoId = id, title = originalTitle, duration = duration, artist = artist
)