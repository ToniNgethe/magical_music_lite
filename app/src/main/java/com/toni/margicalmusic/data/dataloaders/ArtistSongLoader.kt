package com.toni.margicalmusic.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.toni.margicalmusic.domain.models.Song
import javax.inject.Inject


class ArtistSongLoader @Inject constructor (val context: Context) {

    fun getSongsForArtist(artistID: Long): ArrayList<Song> {
        val cursor = makeArtistSongCursor(context, artistID)
        val songsList = arrayListOf<Song>()
        if (cursor != null && cursor.moveToFirst()) do {
            val id = cursor.getLong(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val album = cursor.getString(3)
            val duration = cursor.getInt(4)
            val trackNumber = cursor.getInt(5)
            val albumId = cursor.getInt(6)
            val artistId = artistID

            songsList.add(
                Song(
                    id,
                    albumId.toLong(),
                    artistID.toInt(),
                    title,
                    artist,
                    album,
                    duration,
                    trackNumber
                )
            )
        } while (cursor.moveToNext())
        cursor?.close()
        return songsList
    }


    private fun makeArtistSongCursor(context: Context, artistID: Long): Cursor? {
        val contentResolver = context.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val string = "is_music=1 AND title != '' AND artist_id=$artistID"
        return contentResolver.query(
            uri,
            arrayOf("_id", "title", "artist", "album", "duration", "track", "album_id"),
            string,
            null,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS + " DESC"
        )
    }
}