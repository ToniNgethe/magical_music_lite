package com.toni.margicalmusic.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicText(val message: String) : UiText()
    data class StaticText(@StringRes val stringId: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicText -> this.message
            is StaticText -> context.getString(this.stringId)
        }
    }
}