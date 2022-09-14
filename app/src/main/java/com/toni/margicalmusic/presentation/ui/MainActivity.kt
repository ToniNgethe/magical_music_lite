package com.toni.margicalmusic.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core_navigation.Routes
import com.example.core_navigation.UiEvent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.magicalmusic.core_design.DarkPrimary
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.core_media.models.Song
import com.margicalmusic.core_utils.GsonParser
import com.margicalmusic.feature_onboarding.presentation.on_boarding.OnBoardingScreen
import com.margicalmusic.feature_onboarding.presentation.splashscreen.SplashScreen
import com.margicalmusic.feature_settings.presentation.SettingsPage
import com.toni.margicalmusic.presentation.home.HomePage
import com.magicalmusic.feature_selected_song.presentation.SelectedSongScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MargicalMusicAppTheme {
                val systemUiController = rememberSystemUiController()
                val userDarkIcons = MaterialTheme.colors.isLight
                val context = LocalContext.current

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent, darkIcons = userDarkIcons
                    )
                    systemUiController.setStatusBarColor(if (userDarkIcons) Color.White else DarkPrimary)
                    systemUiController.setNavigationBarColor(if (userDarkIcons) Color.White else DarkPrimary)
                }

                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(
                        navController = navController, startDestination = Routes.SPLASH_SCREEN
                    ) {
                        composable(Routes.SPLASH_SCREEN) {
                            SplashScreen(onNavigate = {
                                navController.navigate(it.route) {
                                    popUpTo(0)
                                }
                            })
                        }
                        composable(Routes.ONBOARDING_SCREEN) {
                            OnBoardingScreen(onNavigate = {
                                navController.navigate(Routes.HOME_PAGE) {
                                    popUpTo(0)
                                }
                            })
                        }
                        composable(Routes.HOME_PAGE) {
                            HomePage(context) { uiEvent ->
                                if (uiEvent is UiEvent.OnNavigate) {
                                    navController.navigate(uiEvent.route)
                                }
                            }
                        }
                        composable(
                            Routes.SONG_PAGE, arguments = listOf(navArgument("song") {})
                        ) { backSentry ->
                            SelectedSongScreen(context, GsonParser.fromJson(
                                backSentry.arguments?.getString("song")!!,
                                Song::class.java
                            ), lifecycle, navController, onNavigate = { event ->

                            })
                        }
                        composable(Routes.SETTINGS_PAGE) {
                            SettingsPage(onNavigate = navController)
                        }
                    }

                }
            }
        }
    }
}