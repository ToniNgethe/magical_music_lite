package com.toni.margicalmusic.presentation.ui.utils

sealed class UiEvent {
    object PopBackStack  : UiEvent()
    class OnNavigate( route: String ) : UiEvent()
}