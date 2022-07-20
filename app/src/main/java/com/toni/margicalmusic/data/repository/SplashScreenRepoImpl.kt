package com.toni.margicalmusic.data.repository

import com.toni.margicalmusic.data.local.AppDataStore
import com.toni.margicalmusic.domain.repositories.SplashScreenRepo
import com.toni.margicalmusic.utils.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashScreenRepoImpl @Inject constructor(
    val dataStore: AppDataStore, val appDispatchers: AppDispatchers
) : SplashScreenRepo {
    override suspend fun markUserOnBoarded() = withContext(appDispatchers.io()) {
        dataStore.markUserOnBoarded()
    }

    override fun isUserOnboarded(): Flow<Boolean> =
        dataStore.isUserOnBoarded().flowOn(appDispatchers.io())
}