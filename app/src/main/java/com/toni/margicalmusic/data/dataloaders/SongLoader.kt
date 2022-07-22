package com.toni.margicalmusic.data.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.provider.MediaStore
import android.text.TextUtils
import com.toni.margicalmusic.domain.models.Song
import javax.inject.Inject

class SongLoader @Inject constructor(val context: Context) {
    private val sEmptyList = LongArray(0)

    /**
     * Fetch songs from cursor to list
     */
    private fun getSongsForCursor(limit: Int, cursor: Cursor?): ArrayList<Song> {
        val arrayList = arrayListOf<Song>()
        if (cursor != null && cursor.moveToFirst()) do {
            val id = cursor.getLong(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val album = cursor.getString(3)
            val duration = cursor.getInt(4)
            val trackNumber = cursor.getInt(5)
            val artistId = cursor.getInt(6)
            val albumId = cursor.getLong(7)

            if (limit != 0 && arrayList.size == limit) {
                break
            } else arrayList.add(
                Song(
                    id, albumId, artistId, title, artist, album, duration, trackNumber
                )
            )
        } while (cursor.moveToNext())
        cursor?.close()
        return arrayList
    }

    private fun getSongForCursor(cursor: Cursor?): Song {
        var song: Song? = null
        if (cursor != null && cursor.moveToFirst()) {
            val id = cursor.getLong(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val album = cursor.getString(3)
            val duration = cursor.getInt(4)
            val trackNumber = cursor.getInt(5)
            val artistId = cursor.getInt(6).toLong()
            val albumId = cursor.getLong(7)

            song = Song(id, albumId, artistId.toInt(), title, artist, album, duration, trackNumber)
        }

        cursor?.close()
        return song!!
    }


    private fun getSongListForCursor(cursor: Cursor?): LongArray {
        val cursor: Cursor? = cursor ?: return sEmptyList
        val len = cursor!!.count
        val list = LongArray(len)
        cursor.moveToFirst()
        var columnIndex = -1
        try {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Playlists.Members.AUDIO_ID)
        } catch (notaplaylist: IllegalArgumentException) {
            columnIndex = cursor.getColumnIndexOrThrow(BaseColumns._ID)
        }

        for (i in 0 until len) {
            list[i] = cursor.getLong(columnIndex)
            cursor.moveToNext()
        }
        cursor.close()
        return list
    }

    fun getSongFromPath(songPath: String, context: Context): Song {
        val cr = context.contentResolver

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.DATA
        val selectionArgs = arrayOf(songPath)
        val projection =
            arrayOf("_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id")
        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"

        val cursor = cr.query(uri, projection, "$selection=?", selectionArgs, sortOrder)

        if (cursor != null && cursor.getCount() > 0) {
            val song = getSongForCursor(cursor)
            cursor.close()
            return song
        } else return Song()
    }

    fun getAllSongs(limit: Int = 0): List<Song> {
        return getSongsForCursor(limit, makeSongCursor(context, "", emptyArray()))
    }

    fun getSongListInFolder(path: String): LongArray {
        val whereArgs = arrayOf("$path%")
        return getSongListForCursor(
            makeSongCursor(
                context, MediaStore.Audio.Media.DATA + " LIKE ?", whereArgs, ""
            )
        )
    }

    fun getSongForID(id: Long): Song {
        return getSongForCursor(makeSongCursor(context, "_id=" + id.toString(), emptyArray()))
    }

    fun searchSongs(searchString: String, limit: Int): List<Song> {
        val result = getSongsForCursor(
            limit = 0, makeSongCursor(context, "title LIKE ?", arrayOf("$searchString%"))
        )
        if (result.size < limit) {
            result.addAll(
                getSongsForCursor(
                    limit = 0, makeSongCursor(
                        context, "title LIKE ?", arrayOf("%_$searchString%")
                    )
                )
            )
        }
        return if (result.size < limit) result else result.subList(0, limit)
    }

    private fun makeSongCursor(
        context: Context, selection: String, paramArrayOfString: Array<String>
    ): Cursor? {
        return makeSongCursor(
            context, selection, paramArrayOfString, MediaStore.Audio.Media.DATE_ADDED + " DESC"
        )
    }

    private fun makeSongCursor(
        context: Context, selection: String, paramArrayOfString: Array<String>, sortOrder: String
    ): Cursor? {
        var selectionStatement = "is_music=1 AND title != ''"

        if (!TextUtils.isEmpty(selection)) {
            selectionStatement = "$selectionStatement AND $selection"
        }
        return context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
                "_id", "title", "artist", "album", "duration", "track", "artist_id", "album_id"
            ), selectionStatement, paramArrayOfString, sortOrder
        )

    }
}