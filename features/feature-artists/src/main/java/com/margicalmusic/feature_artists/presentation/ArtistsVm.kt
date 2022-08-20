package com.margicalmusic.feature_artists.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.feature_artists.domain.ArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsVm @Inject constructor(val artistsRepository: ArtistsRepository) : ViewModel() {

    private val _artistsUiState = MutableStateFlow(ArtistsUiState())
    val artistsUiState: MutableStateFlow<ArtistsUiState> get() = _artistsUiState

    init {
        fetchArtists()
    }

    private fun fetchArtists() {
        viewModelScope.launch {
            artistsRepository.fetchArtists().collectLatest { uiState ->
                when (uiState) {
                    is ResponseState.Success -> {
                        _artistsUiState.emit(_artistsUiState.value.copy(artists = uiState.data))
                    }

                    is ResponseState.Error -> {
                        _artistsUiState.emit(_artistsUiState.value.copy(errorMessage = uiState.uiText))
                    }
                }
            }


        }
    }

}