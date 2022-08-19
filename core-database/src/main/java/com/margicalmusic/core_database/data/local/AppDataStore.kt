package com.margicalmusic.core_database.data.local

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun markUserOnBoarded()
    fun isUserOnBoarded(): Flow<Boolean>
    suspend fun toggleTheme()
    fun getTheme(): Flow<Boolean>
}