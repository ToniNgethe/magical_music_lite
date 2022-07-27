package com.toni.margicalmusic.presentation.songs_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SongsVm @Inject constructor(val songsRepository: SongsRepository) : ViewModel() {

    private val _songsUiState = MutableStateFlow(SongsUiState(isLoading = true))
    val songsUiState = _songsUiState.asStateFlow()
    private val songsList = mutableListOf<Song>()

    private var searchJob: Job? = null

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            val songs = songsRepository.fetchSongs()
            songs.collectLatest { songsState ->
                when (songsState) {
                    is ResponseState.Success -> {
                        songsList.apply {
                            clear()
                            addAll(songsState.data)
                        }
                        _songsUiState.update { it.copy(isLoading = false, songs = songsState.data) }
                    }

                    is ResponseState.Error -> {
                        _songsUiState.update {
                            it.copy(
                                isLoading = false, errorMessage = songsState.uiText
                            )
                        }
                    }
                }

            }
        }
    }

    fun searchList(keyWord: String) {
        if (keyWord == "") {
            _songsUiState.update { it.copy(songs = songsList) }
            return
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val filteredList = mutableListOf<Song>()

            _songsUiState.value.songs?.forEach { song ->
                if (song.title?.lowercase()?.contains(keyWord.lowercase()) == true) {
                    filteredList.add(song)
                }
            }
            if (filteredList.isNotEmpty()) {
                _songsUiState.update { it.copy(songs = filteredList) }
            } else {
                _songsUiState.update { it.copy(songs = songsList) }
            }
        }
    }
}