package com.toni.margicalmusic.utils

sealed class MediaState<T> {
    data class Success<T>(val data: T) : MediaState<T>()
    data class Error<T>(val uiText: UiText) : MediaState<T>()
}