package com.margicalmusic.feature_settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.margicalmusic.core_database.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val isDarkMode: Boolean
)

@HiltViewModel
class SettingsVm @Inject constructor(val settingsRepository: SettingsRepository) : ViewModel() {
    private val _settingsUiState = MutableStateFlow(SettingsUiState(isDarkMode = false))
    val settingsUiState = _settingsUiState.asStateFlow()

    init {
        fetchTheme()
    }

    private fun fetchTheme() {
        viewModelScope.launch {
            settingsRepository.checkTheme().collectLatest { themeStatus ->
                _settingsUiState.update { it.copy(isDarkMode = themeStatus) }
            }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            settingsRepository.toggleTheme()
        }
    }
}