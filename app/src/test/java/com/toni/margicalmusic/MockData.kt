package com.toni.margicalmusic

import com.toni.margicalmusic.data.database.SongsEntity
import com.toni.margicalmusic.domain.models.*

val artist = Artist(id = 0, name = "", songCount = 0, albumCount = 0, image = null)
val song = Song(
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
    name = "", song = Song(
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
val video = Video(
    videoId = "QRBNDS", title = "new song", duration = null, artist = "artist name"

)

val lyricModel = Lyric(lyrics = "sample lyrics")