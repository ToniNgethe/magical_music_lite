package com.toningethe.margicalmusic.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.toni.margicalmusic.domain.models.Album


object ArtistAlbumLoader {

    fun getAlbumsForArtist(context: Context, artistID: Long): ArrayList<Album> {

        val albumList = ArrayList<Album>()
        val cursor = makeAlbumForArtistCursor(context, artistID)

        if (cursor != null) {
            if (cursor.moveToFirst()) do {

                val album = Album(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    artistID,
                    cursor.getInt(3),
                    cursor.getInt(4),
                    1
                )
                albumList.add(album)
            } while (cursor.moveToNext())

        }
        cursor?.close()
        return albumList
    }


    fun makeAlbumForArtistCursor(context: Context, artistID: Long): Cursor? {

        if (artistID.toInt() == -1) return null

        return context.contentResolver.query(
                MediaStore.Audio.Artists.Albums.getContentUri("external", artistID),
                arrayOf("_id", "album", "artist", "numsongs", "minyear"),
                null,
                null,
                MediaStore.Audio.Albums.FIRST_YEAR
            )
    }
}