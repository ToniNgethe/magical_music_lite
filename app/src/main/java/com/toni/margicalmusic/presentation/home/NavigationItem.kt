package com.toni.margicalmusic.presentation.home

import com.toni.margicalmusic.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Songs : NavigationItem("songs", R.drawable.ic_music, "Songs")
    object Artists : NavigationItem("artists", R.drawable.ic_users, "Artists")
    object Albums : NavigationItem("albums", R.drawable.ic_albums, "Albums")
    object Trending : NavigationItem("trending", R.drawable.ic_trending, "Trending")
}