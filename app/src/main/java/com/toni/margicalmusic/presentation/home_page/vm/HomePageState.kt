package com.toni.margicalmusic.presentation.home_page.vm

import com.toni.margicalmusic.domain.models.Album
import com.toni.margicalmusic.domain.models.Artist
import com.toni.margicalmusic.domain.models.GenreSongModel
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.utils.UiText

data class HomePageState(
    val errorMessage: UiText? = null,
    val albums: List<Album>? = null,
    val artists: List<Artist>? = null,
    val artistsError: UiText? = null,
    val songs: List<Song>? = null,
    val songsError: UiText? = null,
    val genres: List<GenreSongModel>? = null,
    val genresError: UiText? = null,
    val isLoading: Boolean = false
)