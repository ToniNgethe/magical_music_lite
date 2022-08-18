package com.toni.margicalmusic.presentation.atists

import com.margicalmusic.core_utils.UiText


data class ArtistsUiState(
    var errorMessage: UiText? = null, var artists: List<com.margicalmusic.core_media.models.Artist>? = null
)