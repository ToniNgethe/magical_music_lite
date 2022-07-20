package com.toni.margicalmusic.presentation.on_boarding

import android.Manifest
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.on_boarding.components.OnBoardingItem
import com.toni.margicalmusic.presentation.splash.SplashScreenVm
import com.toni.margicalmusic.presentation.splash.UserOnboarded
import com.toni.margicalmusic.presentation.theme.Ascent
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.theme.gray_a
import com.toni.margicalmusic.presentation.ui.utils.Routes
import com.toni.margicalmusic.presentation.ui.utils.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: SplashScreenVm = hiltViewModel(), onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    MargicalMusicAppTheme {
        val bottomSheetState =
            rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
        val coroutineScope = rememberCoroutineScope()
        val permissionState =
            rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
        val context = LocalContext.current

        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetPeekHeight = 0.dp,
            sheetContent = {
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
                                    viewModel.onEvent(UserOnboarded.OnBoardUser)
                                    onNavigate(UiEvent.OnNavigate(Routes.HOME_PAGE))
                                } else {
                                    val message = if (permissionState.status.shouldShowRationale) {
                                        "Reading external folder is important for this app. Please grant the permission."
                                    } else {
                                        "Permission required to continue"
                                    }
                                    Toast.makeText(
                                        context, message, LENGTH_SHORT
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
                                text = "Grant Access",
                                style = MaterialTheme.typography.h1.copy(color = Color.White)
                            )
                        }
                    }
                }
            }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                Spacer(modifier = Modifier.weight(2f))

                Box(modifier = Modifier.padding(40.dp)) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                bottomSheetState.bottomSheetState.expand()
                            }
                        },
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
    }
}

