package com.margicalmusic.feature_artists.presentation

import com.margicalmusic.core_utils.UiText


data class ArtistsUiState(
    var errorMessage: UiText? = null, var artists: List<com.margicalmusic.core_media.models.Artist>? = null
)