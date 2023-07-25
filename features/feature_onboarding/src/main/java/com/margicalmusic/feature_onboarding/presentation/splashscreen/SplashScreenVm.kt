package com.margicalmusic.feature_onboarding.presentation.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.margicalmusic.feature_onboarding.domain.SplashScreenRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashScreenUiState(
    val userOnBoarded: Boolean?
)

sealed class UserOnboarded {
    object OnBoardUser : UserOnboarded()
    object OnBoardUserStatus : UserOnboarded()
}

@HiltViewModel
class SplashScreenVm @Inject constructor(val splashScreenRepo: SplashScreenRepo) : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashScreenUiState(false))
    val splashUiState = _splashUiState.asStateFlow()

    init {
        onEvent(UserOnboarded.OnBoardUserStatus)
    }

    fun onEvent(event: UserOnboarded) {
        when (event) {
            is UserOnboarded.OnBoardUser -> {
                viewModelScope.launch {
                    splashScreenRepo.markUserOnBoarded()
                }
            }

            is UserOnboarded.OnBoardUserStatus -> {
                viewModelScope.launch {
                    splashScreenRepo.isUserOnboarded().collectLatest { status ->
                        _splashUiState.update { it.copy(status) }
                    }
                }
            }

            else -> {}
        }
    }

}