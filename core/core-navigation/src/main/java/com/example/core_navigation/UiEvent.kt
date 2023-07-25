package com.example.core_navigation

sealed class UiEvent {
    object PopBackStack  : UiEvent()
    class OnNavigate(val route: String, val args: String? = null ) : UiEvent()
}