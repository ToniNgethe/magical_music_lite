package com.toni.margicalmusic.presentation.songs_page

import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.utils.UiText

data class SongsUiState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null, val songs: List<Song>? = null
)