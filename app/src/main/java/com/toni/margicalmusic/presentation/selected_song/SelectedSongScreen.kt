package com.toni.margicalmusic.presentation.selected_song

import android.content.Context
import android.content.res.Configuration.*
import androidx.annotation.NonNull
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.ui.utils.UiEvent

@Composable
fun SelectedSongScreen(
    context: Context, lifecycle: Lifecycle, onNavigate: (UiEvent.OnNavigate) -> Unit
) {
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close page",
                        tint = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = "Song title goes here",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                val youtubePlayer = remember {
                    YouTubePlayerView(context).apply {
                        lifecycle.addObserver(this)
                        enableAutomaticInitialization = false
                        initialize(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.cueVideo("fV7TZJCa9c8", 0f)
                            }
                        })
                    }
                }
                AndroidView(
                    {
                        youtubePlayer
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

            }
        }
    }
}
