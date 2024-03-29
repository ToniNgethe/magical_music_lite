package com.magicalmusic.feature_song.presentation

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.margicalmusic.core_utils.GsonParser
import com.magicalmusic.core_design.shared_components.HomePageHeader
import com.magicalmusic.core_design.shared_components.CustomSearchField
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.core_media.models.Song
import com.margicalmusic.resources.R
import com.toni.margicalmusic.presentation.home_page.components.HomeSongsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongsView(
    songsVm: SongsVm = hiltViewModel(),
    onNavigate: ((UiEvent.OnNavigate) -> Unit)? = null
) {
    val songsUiState = songsVm.songsUiState.collectAsState()

    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            HomePageHeader( onNavigate!! )

            CustomSearchField { text ->
                songsVm.searchList(text)
            }

            val errorMessage = songsUiState.value.errorMessage

            if (errorMessage != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = errorMessage.asString(LocalContext.current),
                        style = MaterialTheme.typography.h1.copy(fontSize = 13.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            val songs = songsUiState.value.songs
            // songs row
            if (songs?.isNotEmpty() == true) LazyColumn {
                items(songs.size) { index ->
                    HomeSongsItem(
                        modifier = Modifier.animateItemPlacement(),
                        index,
                        songs[index],
                        icon = R.drawable.ic_play,
                        onClick = { song ->
                            onNavigate?.invoke(
                                UiEvent.OnNavigate(
                                    "song_page?song=${
                                        GsonParser.toJson(
                                            song, Song::class.java
                                        )
                                    }"
                                )
                            )
                        })
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark theme")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "white theme")
@Composable
fun SongsViewPreview() {
    SongsView {

    }
}