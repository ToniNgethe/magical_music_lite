package com.margicalmusic.core_media.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.margicalmusic.core_media.models.Album

class AlbumLoader(val context: Context) {

    fun getAlbum(cursor: Cursor?): Album {
        var album = Album()
        if (cursor != null) {
            if (cursor.moveToFirst()) album = Album(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getLong(3),
                cursor.getInt(4),
                cursor.getInt(5)
            )
        }
        cursor?.close()
        return album
    }

    private fun getAlbumsForCursor(cursor: Cursor?): List<Album> {
        val arrayList = ArrayList<Album>()
        if (cursor != null && cursor.moveToFirst()) do {
            arrayList.add(
                Album(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    view = 1
                )
            )
        } while (cursor.moveToNext())
        cursor?.close()
        return arrayList
    }


    fun getAllAlbums(): List<Album> {
        return getAlbumsForCursor(makeAlbumCursor("", emptyArray()))
    }

    fun getAlbum(id: Long): Album {
        return getAlbum(makeAlbumCursor("_id=?", arrayOf(id.toString())))
    }

    fun getAlbums(paramString: String, limit: Int): List<Album> {
        val result = getAlbumsForCursor(makeAlbumCursor("album LIKE ?", arrayOf("$paramString%")))
        if (result.size < limit) {
            //  result.addAll(getAlbumsForCursor(makeAlbumCursor(context, "album LIKE ?", arrayOf("%_$paramString%"))))
        }
        return if (result.size < limit) result else result.subList(0, limit)
    }

    private fun makeAlbumCursor(
        selection: String, paramArrayOfString: Array<String>
    ): Cursor? {
        return context.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "album", "artist", "artist_id", "numsongs", "minyear"),
            selection,
            paramArrayOfString,
            MediaStore.Audio.Albums.FIRST_YEAR + " DESC"
        )
    }
}