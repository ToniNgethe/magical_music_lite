package com.toni.margicalmusic.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SplashScreenRepo {
    suspend fun markUserOnBoarded()
    fun isUserOnboarded() : Flow<Boolean>
}