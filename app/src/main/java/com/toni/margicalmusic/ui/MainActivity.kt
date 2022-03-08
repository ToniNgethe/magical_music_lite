package com.toni.margicalmusic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.toni.margicalmusic.ui.presentation.SplashScreen
import com.toni.margicalmusic.ui.presentation.home.HomePage
import com.toni.margicalmusic.ui.presentation.on_boarding.OnBoardingScreen
import com.toni.margicalmusic.ui.presentation.theme.DarkPrimary
import com.toni.margicalmusic.ui.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.ui.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MargicalMusicAppTheme {
                val systemUiController = rememberSystemUiController()
                val userDarkIcons = MaterialTheme.colors.isLight

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
                                navController.navigate(Routes.ONBOARDING_SCREEN)
                            })
                        }
                        composable(Routes.ONBOARDING_SCREEN) {
                            OnBoardingScreen(onNavigate = {
                                navController.navigate(Routes.HOME_PAGE)
                            })
                        }
                        composable(Routes.HOME_PAGE) {
                            HomePage()
                        }
                    }

                }
            }
        }
    }
}