package com.toni.margicalmusic.presentation.home_page

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.Ascent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.core_media.models.Song
import com.margicalmusic.core_utils.GsonParser
import com.margicalmusic.core_utils.MediaUtils.getAlbumArtUri
import com.toni.margicalmusic.presentation.home.NavigationItem
import com.toni.margicalmusic.presentation.home_page.components.CategoriesView
import com.toni.margicalmusic.presentation.home_page.vm.HomePageViewModel
import com.magicalmusic.core_design.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.home_page.components.HomeSongsItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePageView(
    homePageViewModel: HomePageViewModel = hiltViewModel(),
    onNavigate: ((UiEvent.OnNavigate) -> Unit)? = null
) {

    val uiState = homePageViewModel.homePageState.collectAsState()
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            // header
            HomePageHeader(onNavigate!!)

            // categories
            val categories = uiState.value.genres
            val catgeoriesErrorMessage = uiState.value.errorMessage

            if (categories?.isNotEmpty() == true) LazyRow(modifier = Modifier.padding(top = 10.dp)) {
                items(categories.size) { index ->
                    CategoriesView(
                        title = categories[index].name,
                        imageUri = getAlbumArtUri(albumId = categories[index].song.albumId),
                        color = categories[index].getGenreColor()
                    )
                }
            }

            catgeoriesErrorMessage?.let { uiText ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = uiText.asString(LocalContext.current),
                        style = MaterialTheme.typography.h1.copy(fontSize = 13.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
                    .background(color = Color(0x26707070))
                    .height(1.dp)
            ) {}

            // artists row
            val artists = uiState.value.artists
            val artistMessage = uiState.value.artistsError

            TitleRow("Artists") {

            }
            if (artists?.isNotEmpty() == true) Box(modifier = Modifier.padding(start = 10.dp)) {
                LazyRow {
                    items(artists.size, key = { index -> artists[index].id }) { index ->
                        ArtistViewItem(
                            modifier = Modifier.animateItemPlacement(), artists[index]
                        )
                    }
                }
            }

            artistMessage?.let { uiText ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = uiText.asString(LocalContext.current),
                        style = MaterialTheme.typography.h1.copy(fontSize = 13.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
                    .background(color = Color(0x26707070))
                    .height(1.dp)
            ) {}


            // artists row
            TitleRow("Songs", moreText = "Show more") {
                onNavigate?.invoke(
                    UiEvent.OnNavigate(
                        route = NavigationItem.Songs.route
                    )
                )
            }

            // songs row
            val songs = uiState.value.songs
            val songError = uiState.value.songsError

            if (songs?.isNotEmpty() == true) LazyColumn {
                items(songs.count(), key = { index -> songs[index].id }) { index ->
                    HomeSongsItem(
                        modifier = Modifier.animateItemPlacement(), index, songs[index]
                    ) { song ->
                        onNavigate?.invoke(
                            UiEvent.OnNavigate(
                                "song_page?song=${
                                    GsonParser.toJson(
                                        song, Song::class.java
                                    )
                                }"
                            )
                        )
                    }
                }
            }
            songError?.let { uiText ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = uiText.asString(LocalContext.current),
                        style = MaterialTheme.typography.h1.copy(fontSize = 13.sp),
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}

@Composable
private fun ArtistViewItem(
    modifier: Modifier, artist: com.margicalmusic.core_media.models.Artist
) {
    Column(
        modifier = Modifier.padding(end = 5.dp, bottom = 5.dp)
    ) {
        if (artist.image == null) Box(
            modifier = Modifier
                .background(Color.LightGray)
                .height(90.dp)
                .width(120.dp)
        )

        Text(
            text = artist.name,
            style = MaterialTheme.typography.h2.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = "${artist.songCount} tracks", style = MaterialTheme.typography.h3.copy(
                fontSize = 12.sp, fontStyle = FontStyle.Normal, color = Color(0x6FCECECE)
            ), color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun TitleRow(title: String, moreText: String? = null, onRowClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                onRowClick.invoke()
            }),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface
        )
        if (moreText == null) Icon(
            painter = painterResource(id = com.margicalmusic.resources.R.drawable.ic_right),
            contentDescription = "ic_right",
            tint = Ascent,
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
        else Text(
            moreText, style = MaterialTheme.typography.h2.copy(color = Ascent, fontSize = 14.sp)
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "dark theme")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "white theme")
@Composable
fun HomePageViewPreview() {
    HomePageView()
}