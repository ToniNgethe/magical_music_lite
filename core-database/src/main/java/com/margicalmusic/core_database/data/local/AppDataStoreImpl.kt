package com.margicalmusic.core_database.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "magical_music")


class AppDataStoreImpl @Inject constructor(val context: Context) : AppDataStore {

    // keys
    private val ON_BOARDED = booleanPreferencesKey("on_boarded")
    private val APP_THEME = booleanPreferencesKey("app_theme")

    override suspend fun markUserOnBoarded() {
        context.dataStore.edit { prefs ->
            prefs[ON_BOARDED] = true
        }
    }

    override fun isUserOnBoarded(): Flow<Boolean> = context
        .dataStore
        .data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { prefs ->
            prefs[ON_BOARDED] ?: false
        }

    override suspend fun toggleTheme() {
        val currentThemeStatus = context.dataStore.data.map { it[APP_THEME] ?: false }
        context.dataStore.edit { it[APP_THEME] = !currentThemeStatus.first() }
    }

    override fun getTheme(): Flow<Boolean> = context.dataStore.data.map { it[APP_THEME] ?: false }
}