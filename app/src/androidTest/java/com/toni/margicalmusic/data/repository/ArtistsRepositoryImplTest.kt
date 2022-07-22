package com.toni.margicalmusic.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toni.margicalmusic.TestUtiDispatchers
import com.toni.margicalmusic.data.local.AppDataStore
import com.toni.margicalmusic.domain.repositories.SplashScreenRepo
import com.toni.margicalmusic.utils.AppDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class TestAppDataStore constructor(testContext: Context) :
    AppDataStore {
    private val ON_BOARDED = booleanPreferencesKey("on_boarded")

    private val testDataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { testContext.preferencesDataStoreFile("test_data_store") })

    override suspend fun markUserOnBoarded() {
        testDataStore.edit { prefs ->
            prefs[ON_BOARDED] = true
        }
    }

    override fun isUserOnBoarded(): Flow<Boolean> = testDataStore.data.map { prefs ->
        prefs[ON_BOARDED] ?: false
    }
}

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ArtistsRepositoryImplTest {
    private lateinit var dataStore: AppDataStore
    private lateinit var appDispatchers: AppDispatchers
    private lateinit var splashScreenRepo: SplashScreenRepo

    private val testContext: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        appDispatchers = TestUtiDispatchers()
        dataStore = TestAppDataStore(testContext = testContext)

        splashScreenRepo = SplashScreenRepoImpl(dataStore, appDispatchers = appDispatchers)
    }

    @Test
    fun shouldMarkUserAsOnBoarded() = runTest {
        splashScreenRepo.markUserOnBoarded()
        val isUserOnboarded = splashScreenRepo.isUserOnboarded().first()
        assert(isUserOnboarded)
    }
}