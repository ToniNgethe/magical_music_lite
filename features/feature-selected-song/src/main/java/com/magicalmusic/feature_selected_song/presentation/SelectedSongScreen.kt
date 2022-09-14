package com.magicalmusic.feature_selected_song.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.Ascent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.resources.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@Composable
fun SelectedSongScreen(
    context: Context,
    song: com.margicalmusic.core_media.models.Song?,
    lifecycle: Lifecycle,
    navController: NavHostController,
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: SelectedSongVm = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchSongDetails(song?.title ?: "", song?.artistName ?: "", song?.id)
    }


    MargicalMusicAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 18.dp, end = 18.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close page",
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Column {
                        Text(
                            text = "${song?.title}",
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "${song?.artistName}",
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (uiState.isLoading) Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(
                    )
                ) {
                    CircularProgressIndicator(
                        color = Ascent
                    )
                }

                if (uiState.errorMessage != null) Text(
                    uiState.errorMessage!!.asString(context = context),
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )

                Column(modifier = Modifier.fillMaxWidth()) {

                    if (uiState.videoError != null) {
                        Text(
                            uiState.videoError.asString(context = context),
                            modifier = Modifier
                                .padding(all = 30.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    if (uiState.video != null) {
                        val youtubePlayer = remember {
                            YouTubePlayerView(context).apply {
                                lifecycle.addObserver(this)
                                enableAutomaticInitialization = false
                                initialize(object : AbstractYouTubePlayerListener() {
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.loadVideo("${uiState.video!!.videoId}", 0f)
                                    }
                                })
                            }
                        }
                        AndroidView(
                            {
                                youtubePlayer
                            },
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }

                    if (uiState.lyricsError != null) {
                        Text(
                            uiState.lyricsError.asString(context = context),
                            modifier = Modifier
                                .padding(all = 30.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface
                        )
                    }

                    if (uiState.lyrics != null) Text(
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        text = uiState.lyrics ?: ""
                    )
                }
            }
        }
    }
}
