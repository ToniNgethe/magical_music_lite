package com.toni.margicalmusic.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toni.margicalmusic.presentation.home_page.HomePageView
import com.toni.margicalmusic.presentation.songs_page.SongsView
import com.toni.margicalmusic.presentation.ui.utils.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(context: Context, onUiEvent: (UiEvent) -> Unit) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        Navigation(navController, context, onUiEvent)
    }
}

@Composable
fun Navigation(
    navController: NavHostController, context: Context, onUiEvent: (UiEvent) -> Unit
) {
    NavHost(
        navController,
        startDestination = NavigationItem.Home.route,
        modifier = Modifier.padding(bottom = 56.dp)
    ) {
        composable(NavigationItem.Home.route) {
            HomePageView(onNavigate = onUiEvent)
        }
        composable(NavigationItem.Songs.route) {
            SongsView(onEvent = onUiEvent)
        }
        composable(NavigationItem.Artists.route) {
            ArtistsView(context)
        }
        composable(NavigationItem.Trending.route) {
            TrendingView()
        }
    }
}