package com.toni.margicalmusic.presentation.selected_song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.models.Video
import com.toni.margicalmusic.domain.usecases.GetLyricsAndVideoUseCase
import com.toni.margicalmusic.utils.ResponseState
import com.toni.margicalmusic.utils.UiText
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
                    errorMessage = UiText.StaticText(R.string.song_details_error)
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
                    lyricsError = null,
                    isLoading = false,
                    lyrics = null,
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
                    videoError = null,
                    isLoading = false,
                    lyrics = null,
                    video = null
                )
            }
        }
    }
}