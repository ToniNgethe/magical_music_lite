package com.toni.margicalmusic.presentation.songs_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.utils.MediaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsVm @Inject constructor(val songsRepository: SongsRepository) : ViewModel() {

    private val _songsUiState = MutableStateFlow(SongsUiState(isLoading = true))
    val songsUiState = _songsUiState.asStateFlow()

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            val songs = songsRepository.fetchSongs()
            songs.collectLatest { songsState ->
                when (songsState) {
                    is MediaState.Success -> {
                        _songsUiState.emit(
                            _songsUiState.value.copy(
                                isLoading = false, songs = songsState.data
                            )
                        )
                    }

                    is MediaState.Error -> {
                        _songsUiState.emit(
                            _songsUiState.value.copy(
                                isLoading = false, errorMessage = songsState.uiText
                            )
                        )
                    }
                }

            }
        }
    }

}