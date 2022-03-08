package com.toni.margicalmusic.ui.utils

sealed class UiEvent {
    object PopBackStack  : UiEvent()
    class OnNavigate( route: String ) : UiEvent()
}