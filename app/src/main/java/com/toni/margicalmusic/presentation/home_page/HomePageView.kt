package com.toni.margicalmusic.ui.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.sampleArtistsData
import com.toni.margicalmusic.domain.models.sampleDaya
import com.toni.margicalmusic.presentation.home_page.components.CategoriesView
import com.toni.margicalmusic.presentation.theme.Ascent
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomePageView() {
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            // header
            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(), Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Good  morning",
                    color = Ascent,
                    style = MaterialTheme.typography.h1.copy(fontSize = 24.sp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "search",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            // categories
            LazyRow {
                items(sampleDaya.size) { index ->
                    CategoriesView(
                        title = sampleDaya[index].title,
                        image = sampleDaya[index].image,
                        color = sampleDaya[index].color
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // artists row
            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Text(
                    text = "Artists",
                    style = MaterialTheme.typography.h1.copy(fontSize = 14.sp),
                    color = MaterialTheme.colors.onSurface
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = "ic_right",
                    tint = Ascent,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
            Box( modifier = Modifier.padding(start = 10.dp) ) {
                LazyRow {
                    items(sampleArtistsData.size) { index ->
                        Column {
                            Image(
                                painter = painterResource(id = sampleArtistsData[index].image),
                                contentDescription = "",
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(120.dp)
                            )
                            Text(
                                text = sampleArtistsData[index].name,
                                style = MaterialTheme.typography.h2.copy(fontSize = 14.sp),
                                color = MaterialTheme.colors.onSurface
                            )
                            Text(
                                text = "${sampleArtistsData[index].noTracks} tracks",
                                style = MaterialTheme.typography.h3.copy(
                                    fontSize = 12.sp, fontStyle = FontStyle.Normal,
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
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "dark theme")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, name = "white theme")
@Composable
fun HomePageViewPreview() {
    HomePageView()
}