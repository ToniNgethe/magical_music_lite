package com.toni.margicalmusic.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.home_page.HomePageView
import com.toni.margicalmusic.presentation.songs_page.SongsView
import com.toni.margicalmusic.presentation.theme.Ascent
import com.toni.margicalmusic.presentation.ui.utils.UiEvent

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    context: Context, onUiEvent: (UiEvent) -> Unit
) {
    val navController = rememberNavController()
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(
            BottomSheetValue.Collapsed
        )
    )
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    BottomSheetScaffold(scaffoldState = bottomSheetState, sheetPeekHeight = 0.dp, sheetContent = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Ascent)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string.permission_music_folder),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 24.sp, color = Color.White
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_music_folder),
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.permission_description),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.body2.copy(
                    color = Color.White, fontSize = 16.sp
                )
            )

            Box(modifier = Modifier.padding(10.dp)) {
                Button(
                    onClick = {
                        if (permissionState.status == PermissionStatus.Granted) {

                        } else {
                            val message = if (permissionState.status.shouldShowRationale) {
                                context.getString(R.string.request_permission)
                            } else {
                                context.getString(R.string.permission_required)
                            }
                            Toast.makeText(
                                context, message, Toast.LENGTH_SHORT
                            ).show()
                            permissionState.launchPermissionRequest()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(R.string.grant_access),
                        style = MaterialTheme.typography.h1.copy(color = Color.White)
                    )
                }
            }
        }
    }) {
        if (permissionState.status == PermissionStatus.Granted) {
            Scaffold(bottomBar = {
                BottomNavigationBar(
                    navController
                )
            }) {
                Navigation(navController, context, onUiEvent)
            }
        } else {
            LaunchedEffect(bottomSheetState.bottomSheetState) {
                bottomSheetState.bottomSheetState.expand()
            }
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
            ArtistsView(context)
        }
    }
}