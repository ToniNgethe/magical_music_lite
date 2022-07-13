package com.toni.margicalmusic.presentation.selected_song

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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.presentation.theme.Ascent
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.ui.utils.UiEvent

@Composable
fun SelectedSongScreen(
    context: Context,
    song: Song?,
    lifecycle: Lifecycle,
    navController: NavHostController,
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    viewModel: SelectedSongVm = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchSongDetails(song?.title ?: "", song?.artistName ?: "")
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
                        .padding(all = 18.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close page",
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Text(
                        text = "${song?.title}",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (uiState.isLoading) CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Ascent)

                if (uiState.errorMessage != null) Text(
                    uiState.errorMessage!!.asString(context = context),
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface
                )

                if (uiState.lyrics != null) Column(modifier = Modifier.fillMaxWidth()) {
                    val youtubePlayer = remember {
                        YouTubePlayerView(context).apply {
                            lifecycle.addObserver(this)
                            enableAutomaticInitialization = false
                            initialize(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo("nlMYTD_pHBA", 0f)
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

                    Text(
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
