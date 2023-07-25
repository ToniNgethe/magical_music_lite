package com.margicalmusic.feature_onboarding.presentation.on_boarding

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_navigation.Routes
import com.example.core_navigation.UiEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.magicalmusic.core_design.Ascent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.magicalmusic.core_design.shared_components.BottomSheetPermissionContent
import com.margicalmusic.feature_onboarding.presentation.splashscreen.SplashScreenVm
import com.margicalmusic.feature_onboarding.presentation.splashscreen.UserOnboarded
import com.margicalmusic.feature_onboarding.presentation.on_boarding.components.OnBoardingItem
import com.margicalmusic.resources.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: SplashScreenVm = hiltViewModel(), onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    MargicalMusicAppTheme {
        val bottomSheetState =
            rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
        var permissionAsked by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val permissionState =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                /**
                 *  starting from Android 13, a new permission called READ_MEDIA_AUDIO is added, which is necessary for an app to be able to
                 *  read audio files from external storage. This permission is a spin-off of READ_EXTERNAL_STORAGE for audio only.
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    rememberPermissionState(
                        Manifest.permission.READ_MEDIA_AUDIO
                    )
                } else {
                    rememberPermissionState(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            } else {
                rememberPermissionState(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        val context = LocalContext.current

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
                    if (!permissionState.status.shouldShowRationale && permissionAsked) {
                        context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        })

                    } else
                        when {
                            permissionState.status.shouldShowRationale -> {
                                permissionAsked = true
                                val message =
                                    "Reading external folder is important for this app. Please grant the permission."
                                Toast.makeText(
                                    context, message, Toast.LENGTH_SHORT
                                ).show()

                                permissionState.launchPermissionRequest()
                            }

                            permissionState.status.isGranted -> {
                                viewModel.onEvent(UserOnboarded.OnBoardUser)
                                onNavigate(UiEvent.OnNavigate(Routes.HOME_PAGE))
                            }

                            !permissionState.status.isGranted -> {
                                permissionState.launchPermissionRequest()
                            }
                        }

                })
            }) {
            OnBoardingScreenContent(onClick = {
                coroutineScope.launch {
                    bottomSheetState.bottomSheetState.expand()
                }
            })
        }
    }
}


@Composable
private fun OnBoardingScreenContent(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_logo), contentDescription = "logo"
        )
        Text(
            text = stringResource(R.string.magical_music_app),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.secondary,
            fontSize = 20.sp,
            modifier = Modifier.padding(all = 10.dp)
        )
        Spacer(modifier = Modifier.padding(all = 20.dp))

        OnBoardingItem(
            title = "Get videos, artists alternatives and lyrics of \n" + "your Local music. ",
            icon = R.drawable.ic_music
        )

        Spacer(modifier = Modifier.padding(all = 10.dp))

        OnBoardingItem(
            title = "Bringing more to your  music, artists and \nplaylist",
            icon = R.drawable.ic_playlist
        )
        Spacer(modifier = Modifier.padding(all = 10.dp))
        OnBoardingItem(
            title = "Discover your favourite artist albums, songs \n" + "and music videos",
            icon = R.drawable.ic_albums
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(modifier = Modifier.padding(40.dp)) {
            Button(
                onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Ascent)
            ) {
                Text(
                    text = stringResource(R.string.get_started),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

    }
}


@Preview()
@Composable
fun OnBoardingScreenContentPreview() {
    MargicalMusicAppTheme {
        OnBoardingScreenContent(onClick = {})
    }
}