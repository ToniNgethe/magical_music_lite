package com.margicalmusic.feature_artists.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.magicalmusic.core_design.shared_components.HomePageHeader
import com.magicalmusic.core_design.shared_components.CustomSearchField

@Composable
fun ArtistsView(
    context: Context,
    artistsVm: ArtistsVm = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    val artistsUiState = artistsVm.artistsUiState.collectAsState()

    val artists = artistsUiState.value.artists
    val errorMessage = artistsUiState.value.errorMessage

    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            HomePageHeader(onNavigate)
            CustomSearchField {}

            errorMessage?.let {
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

            if (artists?.isNotEmpty() == true) LazyVerticalGrid(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(3)
            ) {
                items(artists.size) { index ->
                    val artist = artists[index]
                    Column {
                        if (artist.image == null) {
                            Box(
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .height(90.dp)
                                    .fillMaxWidth()
                            )
                        }
                        Text(
                            text = artist.name,
                            style = MaterialTheme.typography.h2.copy(fontSize = 14.sp),
                            color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "${artist.songCount} tracks",
                            style = MaterialTheme.typography.h3.copy(
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Normal,
                                color = Color(0x6FCECECE)
                            ),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}
