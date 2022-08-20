package com.margicalmusic.feature_onboarding.domain

import kotlinx.coroutines.flow.Flow

interface SplashScreenRepo {
    suspend fun markUserOnBoarded()
    fun isUserOnboarded() : Flow<Boolean>
}