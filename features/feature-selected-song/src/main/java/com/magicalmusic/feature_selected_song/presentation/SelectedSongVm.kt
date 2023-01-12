package com.magicalmusic.feature_selected_song.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicalmusic.feature_selected_song.domain.model.Lyric
import com.magicalmusic.feature_selected_song.domain.model.Video
import com.magicalmusic.feature_selected_song.domain.usecases.GetLyricsAndVideoUseCase
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectedSongUiState(
    var isLoading: Boolean = false,
    var errorMessage: UiText? = null,
    val videoError: UiText? = null,
    val lyricsError: UiText? = null,
    var lyrics: String? = null,
    var video: Video? = null
)

@HiltViewModel
class SelectedSongVm @Inject constructor(private val lyricsVideoUseCase: GetLyricsAndVideoUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SelectedSongUiState())
    val uiState = _uiState.asStateFlow()


    fun fetchSongDetails(title: String, artistName: String, songId: Long?) {
        if (title.isEmpty() || artistName.isEmpty()) {
            _uiState.update {
                it.copy(
                    errorMessage = UiText.StaticText(com.margicalmusic.resources.R.string.song_details_error)
                )
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val response = lyricsVideoUseCase.invoke(title = title, artistName = artistName, songId)

            parseLyrics(response)
            parseVideo(response)
        }
    }

    private fun parseVideo(response: Pair<ResponseState<Lyric>, ResponseState<Video>>) {
        when (val video = response.second) {
            is ResponseState.Success -> _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    video = video.data,
                )
            }

            is ResponseState.Error -> _uiState.update {
                it.copy(
                    videoError = video.uiText,
                    isLoading = false,
                    video = null
                )
            }
        }
    }

    private fun parseLyrics(response: Pair<ResponseState<Lyric>, ResponseState<Video>>) {
        when (val lyrics = response.first) {
            is ResponseState.Success -> _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    lyrics = lyrics.data.lyrics,
                )
            }

            is ResponseState.Error -> _uiState.update {
                it.copy(
                    lyricsError = lyrics.uiText,
                    isLoading = false,
                    lyrics = null
                )
            }
        }
    }
}
