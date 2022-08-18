package com.margicalmusic.core_media.dataloaders

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import com.margicalmusic.core_media.models.Artist
import javax.inject.Inject

class ArtistLoader @Inject constructor (val context: Context) {

    fun getArtist(cursor: Cursor?): Artist {
        var artist: Artist? = null
        if (cursor != null) {
            if (cursor.moveToFirst()) artist = Artist(
                cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)
            )
        }
        cursor?.close()
        return artist!!
    }

    private fun getArtistsForCursor(cursor: Cursor?): MutableList<Artist> {
        val arrayList = ArrayList<Artist>()
        if (cursor != null && cursor.moveToFirst()) do {
            arrayList.add(
                Artist(
                    cursor.getLong(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)
                )
            )
        } while (cursor.moveToNext())
        cursor?.close()
        return arrayList
    }

    fun getAllArtists(): List<Artist> {
        return getArtistsForCursor(makeArtistCursor(context, "", emptyArray()))
    }

    fun getArtist(id: Long): Artist {
        return getArtist(makeArtistCursor(context, "_id=?", arrayOf(id.toString())))
    }

    fun getArtists(paramString: String, limit: Int): List<Artist> {
        val result = getArtistsForCursor(
            makeArtistCursor(
                context, "artist LIKE ?", arrayOf("$paramString%")
            )
        )
        if (result.size < limit) {
            //  result.addAll(getArtistsForCursor(makeArtistCursor(context, "artist LIKE ?", arrayOf("%_$paramString%"))))
        }
        return if (result.size < limit) result else result.subList(0, limit)
    }

    private fun makeArtistCursor(
        context: Context, selection: String, paramArrayOfString: Array<String>
    ): Cursor? {
        return context.contentResolver.query(
            MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            arrayOf("_id", "artist", "number_of_albums", "number_of_tracks"),
            selection,
            paramArrayOfString,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS + " DESC"
        )
    }
}