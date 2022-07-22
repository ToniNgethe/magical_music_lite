package com.toni.margicalmusic.data.dataloaders

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.utils.AppDispatchers
import kotlinx.coroutines.*
import javax.inject.Inject

class GenreLoader @Inject constructor(
    val context: Context, val songLoader: SongLoader, val appDispatchers: AppDispatchers
) {

    val mGenresSongCountHashMap = HashMap<String, Int>()
    private val mMediaStoreSelection: String? = null
    val mGenresHashMap = HashMap<String, String>()

    private val scope = CoroutineScope(appDispatchers.io() + Job())

    init {
        scope.launch {
            buildGenresLibrary()
        }
    }

    fun getFirstSongInGenre(genre: String): Song {
        var song: Song? = null
        for ((key, value) in mGenresHashMap) {
            if (value == genre) {
                song = songLoader.getSongFromPath(key, context)
                break
            }
        }
        return song!!
    }

    /**
     * Builds a HashMap of all songs and their genres.
     */
    private fun buildGenresLibrary() {
        //Get a cursor of all genres in MediaStore.
        val genresCursor = context.contentResolver.query(
            MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Audio.Genres._ID, MediaStore.Audio.Genres.NAME),
            null,
            null,
            null
        )

        //Iterate thru all genres in MediaStore.
        genresCursor!!.moveToFirst()

        while (!genresCursor.isAfterLast) {
            val genreId = genresCursor.getString(0)
            var genreName = genresCursor.getString(1)

            if (genreName == null || genreName.isEmpty() || genreName == " " || genreName == "   " || genreName == "    ") genreName =
                "Alternative"

            /* Grab a cursor of songs in the each genre id. Limit the songs to
        	 * the user defined folders using mMediaStoreSelection.
        	 */
            genreId?.let {
                val cursor = context.contentResolver.query(
                    makeGenreUri(genreId),
                    arrayOf(MediaStore.Audio.Media.DATA),
                    mMediaStoreSelection,
                    null,
                    null
                )

                //Add the songs' file paths and their genre names to the hash.
                if (cursor != null) {
                    for (i in 0 until cursor.getCount()) {
                        cursor.moveToPosition(i)
                        mGenresHashMap.put(cursor.getString(0), genreName)
                        mGenresSongCountHashMap.put(genreName, cursor.getCount())
                    }

                    cursor.close()
                }
                genresCursor.moveToNext()
            }
        }

        genresCursor.close()
    }

    /**
     * Returns a Uri of a specific genre in MediaStore.
     * The genre is specified using the genreId parameter.
     */
    private fun makeGenreUri(genreId: String): Uri {
        val contentDir = MediaStore.Audio.Genres.Members.CONTENT_DIRECTORY
        return Uri.parse(
            StringBuilder().append(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI.toString())
                .append("/").append(genreId).append("/").append(contentDir).toString()
        )
    }

    /**
     * Returns the number of songs in the specified genre.
     */
    private fun getGenreSongsCount(genre: String?): Int? {
        return if (mGenresSongCountHashMap != null) if (genre != null) if (mGenresSongCountHashMap.get(
                genre
            ) != null
        ) mGenresSongCountHashMap.get(genre)
        else 0
        else if (mGenresSongCountHashMap.get("Unknown genre") != null) mGenresSongCountHashMap.get("Unknown genre")
        else 0
        else 0
    }

}