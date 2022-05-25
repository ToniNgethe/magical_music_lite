package com.toni.margicalmusic.utils

import android.content.ContentUris
import android.net.Uri

object MediaUtils {
    fun getAlbumArtUri(albumId: Long): Uri {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId)
    }

    /**
     * Convert millisseconds to hh:mm:ss format.
     *
     * @param milliseconds The input time in milliseconds to format.
     * @return The formatted time string.
     */
    fun convertMillisToMinsSecs(milliseconds: Long): String {

        val secondsValue = (milliseconds / 1000).toInt() % 60
        val minutesValue = (milliseconds / (1000 * 60) % 60).toInt()
        val hoursValue = (milliseconds / (1000 * 60 * 60) % 24).toInt()

        var seconds = ""
        var minutes = ""
        var hours = ""

        if (secondsValue < 10) {
            seconds = "0$secondsValue"
        } else {
            seconds = "" + secondsValue
        }

        minutes = "" + minutesValue
        hours = "" + hoursValue

        var output = ""
        if (hoursValue != 0) {
            minutes = "0$minutesValue"
            hours = "" + hoursValue
            output = "$hours:$minutes:$seconds"
        } else {
            minutes = "" + minutesValue
            hours = "" + hoursValue
            output = "$minutes:$seconds"
        }

        return output
    }
}