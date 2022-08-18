package com.toni.margicalmusic.presentation.home

import com.toni.margicalmusic.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", com.margicalmusic.resources.R.drawable.ic_home, "Home")
    object Songs : NavigationItem("songs", com.margicalmusic.resources.R.drawable.ic_music, "Songs")
    object Artists : NavigationItem("artists",com.margicalmusic.resources. R.drawable.ic_users, "Artists")
    object Albums : NavigationItem("albums",com.margicalmusic.resources. R.drawable.ic_albums, "Albums")
    object Trending : NavigationItem("trending",
        com.margicalmusic.resources. R.drawable.ic_trending, "Trending")
}