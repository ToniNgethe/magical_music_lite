package com.magicalmusic.feature_song.presentation

import com.margicalmusic.core_utils.UiText


data class SongsUiState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val songs: List<com.margicalmusic.core_media.models.Song>? = null
)