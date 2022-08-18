package com.toni.margicalmusic.domain.models

import androidx.compose.ui.graphics.Color


data class GenreSongModel(
    val name: String, val song: com.margicalmusic.core_media.models.Song, val count: Int
) {

    fun getGenreColor() : Color {
        val colors = listOf(Color(0xFF264653),
            Color(0xFF2a9d8f),
            Color(0xFFe9c46a),
            Color(0xFFf4a261),
            Color(0xFFe76f51),
            Color(0xFF619b8a),

            )
        return colors.random()
    }

}