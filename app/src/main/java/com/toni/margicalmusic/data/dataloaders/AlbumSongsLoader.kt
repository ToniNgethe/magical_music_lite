package com.toni.margicalmusic.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.toni.margicalmusic.domain.models.Song

object AlbumSongsLoader {

    private val sEmptyList = LongArray(0)

    fun getSongsForAlbum(context: Context, albumID: Long): ArrayList<Song> {

        val cursor = makeAlbumSongCursor(context, albumID)
        val arrayList = ArrayList<Song>()
        if (cursor != null && cursor.moveToFirst())
            do {
                val id = cursor.getLong(0)
                val title = cursor.getString(1)
                val artist = cursor.getString(2)
                val album = cursor.getString(3)
                val duration = cursor.getInt(4)
                var trackNumber = cursor.getInt(5)
                /*This fixes bug where some track numbers displayed as 100 or 200*/
                while (trackNumber >= 1000) {
                    trackNumber -= 1000 //When error occurs the track numbers have an extra 1000 or 2000 added, so decrease till normal.
                }
                val artistId = cursor.getInt(6)

                arrayList.add(Song(id, albumID,
                    artistId, title, artist, album, duration, trackNumber))
            } while (cursor.moveToNext())
        cursor?.close()
        return arrayList
    }

    fun makeAlbumSongCursor(context: Context, albumID: Long): Cursor? {
        val contentResolver = context.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val string = "is_music=1 AND title != '' AND album_id=$albumID"
        return contentResolver.query(
            uri,
            arrayOf("_id", "title", "artist", "album", "duration", "track", "artist_id"),
            string,
            null,
            MediaStore.Audio.Albums.FIRST_YEAR + " DESC"
        )
    }
}