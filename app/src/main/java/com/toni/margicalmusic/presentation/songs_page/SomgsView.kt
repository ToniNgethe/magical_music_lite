package com.toni.margicalmusic.presentation.songs_page

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.Song
import com.toni.margicalmusic.presentation.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.shared_components.HomeSongsItem
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.ui.CustomSearchField
import com.toni.margicalmusic.presentation.ui.utils.UiEvent
import com.toni.margicalmusic.utils.MoshiParser

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
            HomePageHeader()

            CustomSearchField()

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
                                        MoshiParser.toJson(
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