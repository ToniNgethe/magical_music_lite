package com.toni.margicalmusic.presentation.ui.utils

sealed class UiEvent {
    object PopBackStack  : UiEvent()
    class OnNavigate(val route: String, val args: String? = null ) : UiEvent()
}