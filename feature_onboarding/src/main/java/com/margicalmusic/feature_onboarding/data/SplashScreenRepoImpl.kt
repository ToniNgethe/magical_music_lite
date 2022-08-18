package com.margicalmusic.feature_onboarding.data

import com.margicalmusic.core_database.local.AppDataStore
import com.margicalmusic.core_utils.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashScreenRepoImpl @Inject constructor(
    val dataStore: AppDataStore, val appDispatchers: AppDispatchers
) : com.margicalmusic.feature_onboarding.domain.SplashScreenRepo {
    override suspend fun markUserOnBoarded() = withContext(appDispatchers.io()) {
        dataStore.markUserOnBoarded()
    }

    override fun isUserOnboarded(): Flow<Boolean> =
        dataStore.isUserOnBoarded().flowOn(appDispatchers.io())
}