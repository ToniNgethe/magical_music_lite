package com.toni.margicalmusic.utils

sealed class ResponseState<T> {
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val uiText: UiText) : ResponseState<T>()
}
