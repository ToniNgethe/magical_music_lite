package com.margicalmusic.core_database.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun toggleTheme()
    fun checkTheme() : Flow<Boolean>
}