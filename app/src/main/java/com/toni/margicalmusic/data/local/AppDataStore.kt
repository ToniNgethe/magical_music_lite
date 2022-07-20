package com.toni.margicalmusic.data.local

import kotlinx.coroutines.flow.Flow

interface AppDataStore {
    suspend fun markUserOnBoarded()
    fun isUserOnBoarded() : Flow<Boolean>
}