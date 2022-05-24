package com.toni.margicalmusic.presentation.home_page.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.domain.usecases.GetHomePageDataUseCase
import com.toni.margicalmusic.utils.MediaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val getHomePageDataUseCase: GetHomePageDataUseCase) :
    ViewModel() {

    private val _homePageState = MutableStateFlow(HomePageState(isLoading = true))
    val homePageState = _homePageState.asStateFlow()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            val useCase = getHomePageDataUseCase.invoke()
            useCase.collectLatest { tripleData ->
                val (artists, songs, genres) = tripleData
                emitArtists(artists)
                emitSongs(songs)
                emitGenres(genres)
            }
        }
    }

    private suspend fun emitGenres(genres: MediaState<List<GenreSongModel>>) {
        when (genres) {
            is MediaState.Success -> {
                _homePageState.emit(_homePageState.value.copy(genres = genres.data))
            }
            is MediaState.Error -> {
                _homePageState.emit(_homePageState.value.copy(genresError = genres.uiText))
            }
        }
    }

    private suspend fun emitSongs(songs: MediaState<List<Song>>) {
        when (songs) {
            is MediaState.Success -> {
                _homePageState.emit(_homePageState.value.copy(songs = songs.data))
            }
            is MediaState.Error -> {
                _homePageState.emit(_homePageState.value.copy(songsError = songs.uiText))
            }
        }
    }

    private suspend fun emitArtists(artists: MediaState<List<Artist>>) {
        // artists
        when (artists) {
            is MediaState.Success -> {
                _homePageState.emit(_homePageState.value.copy(artists = artists.data))
            }
            is MediaState.Error -> {
                _homePageState.emit(_homePageState.value.copy(artistsError = artists.uiText))
            }
        }
    }
}