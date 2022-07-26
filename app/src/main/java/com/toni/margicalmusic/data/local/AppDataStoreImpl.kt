package com.toni.margicalmusic.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "magical_music")


class AppDataStoreImpl @Inject constructor(val context: Context) : AppDataStore {

    // keys
    private val ON_BOARDED = booleanPreferencesKey("on_boarded")

    override suspend fun markUserOnBoarded() {
        context.dataStore.edit { prefs ->
            prefs[ON_BOARDED] = true
        }
    }

    override fun isUserOnBoarded(): Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[ON_BOARDED] ?: false
    }
}