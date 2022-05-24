package com.toni.margicalmusic.presentation.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toni.margicalmusic.presentation.home_page.HomePageView

@Composable
fun HomePage() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        Navigation(navController)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomePageView()
        }
        composable(NavigationItem.Songs.route) {
            SongsView()
        }
        composable(NavigationItem.Artists.route) {
            ArtistsView()
        }
        composable(NavigationItem.Albums.route) {
            AlbumsView()
        }
        composable(NavigationItem.Trending.route) {
            TrendingView()
        }
    }
}