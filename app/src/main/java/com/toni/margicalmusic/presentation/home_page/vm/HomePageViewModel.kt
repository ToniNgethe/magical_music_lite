package com.toni.margicalmusic.presentation.home_page.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.margicalmusic.core_media.models.Artist
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.margicalmusic.core_media.models.Song
import com.toni.margicalmusic.domain.usecases.GetHomePageDataUseCase
import com.margicalmusic.core_network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
            getHomePageDataUseCase.invoke().collectLatest { tripleData ->
                val (artists, songs, genres) = tripleData
                emitArtists(artists)
                emitSongs(songs)
                emitGenres(genres)
            }
        }
    }

    private fun emitGenres(genres: ResponseState<List<GenreSongModel>>) {
        when (genres) {
            is ResponseState.Success -> {
                _homePageState.update { it.copy(genres = genres.data) }
            }
            is ResponseState.Error -> {
                _homePageState.update { it.copy(genresError = genres.uiText) }
            }
        }
    }

    private fun emitSongs(songs: ResponseState<List<Song>>) {
        when (songs) {
            is ResponseState.Success -> {
                _homePageState.update { it.copy(songs = songs.data) }
            }
            is ResponseState.Error -> {
                _homePageState.update { it.copy(songsError = songs.uiText) }
            }
        }
    }

    private fun emitArtists(artists: ResponseState<List<Artist>>) {
        // artists
        when (artists) {
            is ResponseState.Success -> {
                _homePageState.update { it.copy(artists = artists.data) }
            }
            is ResponseState.Error -> {
                _homePageState.update { it.copy(artistsError = artists.uiText) }
            }
        }
    }
}