package com.margicalmusic.core_database.local

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun markUserOnBoarded()
    fun isUserOnBoarded() : Flow<Boolean>
}