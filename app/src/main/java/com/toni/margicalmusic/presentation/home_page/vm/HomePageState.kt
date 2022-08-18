package com.toni.margicalmusic.presentation.home_page.vm

import com.margicalmusic.core_media.models.Album
import com.margicalmusic.core_media.models.Artist
import com.margicalmusic.core_utils.UiText
import com.toni.margicalmusic.domain.models.GenreSongModel


data class HomePageState(
    val errorMessage: UiText? = null,
    val albums: List<Album>? = null,
    val artists: List<Artist>? = null,
    val artistsError: UiText? = null,
    val songs: List<com.margicalmusic.core_media.models.Song>? = null,
    val songsError: UiText? = null,
    val genres: List<GenreSongModel>? = null,
    val genresError: UiText? = null,
    val isLoading: Boolean = false
)