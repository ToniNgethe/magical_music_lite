package com.margicalmusic.core_database.data.repositories

import com.margicalmusic.core_database.data.local.AppDataStore
import com.margicalmusic.core_database.domain.SettingsRepository
import com.margicalmusic.core_utils.AppDispatchers
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val appDataStore: AppDataStore, val appDispatchers: AppDispatchers
) : SettingsRepository {
    override suspend fun toggleTheme() {
        appDataStore.toggleTheme()
    }

    override fun checkTheme() = appDataStore.getTheme()
}