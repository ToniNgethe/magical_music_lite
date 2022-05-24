package com.toni.margicalmusic.utils

import android.content.ContentUris
import android.net.Uri

object MediaUtils {
    fun getAlbumArtUri(albumId: Long): Uri {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)
    }
}