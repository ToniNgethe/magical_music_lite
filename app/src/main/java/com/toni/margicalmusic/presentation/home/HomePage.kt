package com.toni.margicalmusic.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core_navigation.UiEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.magicalmusic.core_design.shared_components.BottomSheetPermissionContent
import com.toni.margicalmusic.presentation.home_page.HomePageView
import com.magicalmusic.feature_song.presentation.SongsView
import com.margicalmusic.feature_artists.presentation.ArtistsView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    context: Context, onUiEvent: (UiEvent) -> Unit
) {
    val navController = rememberNavController()

    val bottomSheetState =
        rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    var permissionAsked by remember{ mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val permissionState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            /**
             *  starting from Android 13, a new permission called READ_MEDIA_AUDIO is added, which is necessary for an app to be able to
             *  read audio files from external storage. This permission is a spin-off of READ_EXTERNAL_STORAGE for audio only.
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                rememberMultiplePermissionsState(
                    listOf(
                        Manifest.permission.READ_MEDIA_AUDIO
                    )
                )
            } else {
                rememberMultiplePermissionsState(
                    listOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                )
            }
        } else {
            rememberMultiplePermissionsState(listOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        }

    BottomSheetScaffold(scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            BottomSheetPermissionContent(onClick = {
                coroutineScope.launch {
                    bottomSheetState.bottomSheetState.collapse()
                }

                /**
                 *  If shouldShowRationale is false and permissionRequested is true, you can assume that the permissions have been denied permanently:
                 */
                if (!permissionState.shouldShowRationale && permissionAsked) {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    })

                } else
                    when {
                        permissionState.shouldShowRationale -> {
                            val message =
                                "Reading external folder is important for this app. Please grant the permission."
                            Toast.makeText(
                                context, message, Toast.LENGTH_SHORT
                            ).show()

                            permissionState.launchMultiplePermissionRequest()
                        }

                        permissionState.allPermissionsGranted -> {
                        }

                        !permissionState.allPermissionsGranted -> {
                            permissionAsked = true
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }


            })
        }) {
        Scaffold(bottomBar = {
            BottomNavigationBar(
                navController
            )
        }) {

            if (!permissionState.allPermissionsGranted) {
                LaunchedEffect(bottomSheetState.bottomSheetState) {
                    bottomSheetState.bottomSheetState.expand()
                }
            }

            Navigation(navController, context, onUiEvent)
        }
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
            HomePageView { event ->
                if (event.route == NavigationItem.Songs.route) {
                    navController.navigate(NavigationItem.Songs.route)
                } else onUiEvent.invoke(event)
            }
        }
        composable(NavigationItem.Songs.route) {
            SongsView(onNavigate = onUiEvent)
        }
        composable(NavigationItem.Artists.route) {
            ArtistsView(context, onNavigate = onUiEvent)
        }
    }
}
