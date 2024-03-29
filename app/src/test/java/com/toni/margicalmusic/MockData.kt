package com.toni.margicalmusic

import com.margicalmusic.core_database.data.entity.SongsEntity
import com.toni.margicalmusic.domain.models.*

val artist = com.margicalmusic.core_media.models.Artist(
    id = 0,
    name = "",
    songCount = 0,
    albumCount = 0,
    image = null
)
val song = com.margicalmusic.core_media.models.Song(
    id = 0,
    albumId = 0,
    artistId = 0,
    title = null,
    artistName = null,
    albumName = null,
    duration = 0,
    trackNumber = 0,
    trackImage = null,
    mbid = null
)
val genre = GenreSongModel(
    name = "", song = com.margicalmusic.core_media.models.Song(
        id = 0,
        albumId = 0,
        artistId = 0,
        title = null,
        artistName = null,
        albumName = null,
        duration = 0,
        trackNumber = 0,
        trackImage = null,
        mbid = null
    ), count = 0
)

val songEntity = SongsEntity(
    songId = 0,
    title = "new song",
    artistName = "artist name",
    video = "QRBNDS",
    lyrics = "sample lyrics"
)
val video = com.magicalmusic.feature_selected_song.domain.model.Video(
    videoId = "QRBNDS", title = "new song", duration = null, artist = "artist name"

)

val lyricModel = com.magicalmusic.feature_selected_song.domain.model.Lyric(lyrics = "sample lyrics")