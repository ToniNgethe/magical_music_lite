package com.magicalmusic.feature_selected_song.data.mappers

import com.magicalmusic.feature_selected_song.domain.model.Video
import com.margicalmusic.core_database.data.entity.SongsEntity
import com.margicalmusic.core_network.data.dto.lyrics.LyricsResponseDto
import com.margicalmusic.core_network.data.dto.videos.VideoResponseDto

fun SongsEntity.toLyricModel(): com.magicalmusic.feature_selected_song.domain.model.Lyric =
    com.magicalmusic.feature_selected_song.domain.model.Lyric(lyrics = this.lyrics)
fun SongsEntity.toVideoModel(): Video =
    Video(title = title, duration = null, artist = artistName, videoId = video)

fun LyricsResponseDto.toLyricModel(): com.magicalmusic.feature_selected_song.domain.model.Lyric =
    com.magicalmusic.feature_selected_song.domain.model.Lyric(lyrics = data!!)
fun VideoResponseDto.VideoData.toVideoModel(): Video = Video(
    videoId = id, title = originalTitle, duration = duration, artist = artist
)