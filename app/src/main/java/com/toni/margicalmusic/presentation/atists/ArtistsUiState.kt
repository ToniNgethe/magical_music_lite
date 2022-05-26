package com.toni.margicalmusic.presentation.atists

import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.utils.UiText

data class ArtistsUiState(
    var errorMessage: UiText? = null, var artists: List<Artist>? = null
)