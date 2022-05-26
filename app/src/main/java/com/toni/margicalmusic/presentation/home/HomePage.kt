package com.toni.margicalmusic.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toni.margicalmusic.presentation.home_page.HomePageView
import com.toni.margicalmusic.presentation.songs_page.SongsView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(context: Context) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        Navigation(navController, context)
    }
}

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomePageView()
        }
        composable(NavigationItem.Songs.route) {
            SongsView()
        }
        composable(NavigationItem.Artists.route) {
            ArtistsView( context )
        }
        composable(NavigationItem.Albums.route) {
            AlbumsView()
        }
        composable(NavigationItem.Trending.route) {
            TrendingView()
        }
    }
}