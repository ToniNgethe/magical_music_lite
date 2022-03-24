package com.toni.margicalmusic.ui.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.toni.margicalmusic.R
import com.toni.margicalmusic.domain.models.sampleArtistsData
import com.toni.margicalmusic.domain.models.sampleDaya
import com.toni.margicalmusic.domain.models.sampleSonsg
import com.toni.margicalmusic.presentation.home_page.components.CategoriesView
import com.toni.margicalmusic.presentation.home_page.components.HomeSongsItem
import com.toni.margicalmusic.presentation.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.theme.Ascent
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme

@Composable
fun HomePageView() {
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            // header
            HomePageHeader(displaySearchIcon = true)

            // categories
            LazyRow(modifier = Modifier.padding(top = 10.dp)) {
                items(sampleDaya.size) { index ->
                    CategoriesView(
                        title = sampleDaya[index].title,
                        image = sampleDaya[index].image,
                        color = sampleDaya[index].color
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
            TitleRow("Artists") {

            }

            Box(modifier = Modifier.padding(start = 10.dp)) {
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
                    .background(color = Color(0x26707070))
                    .height(1.dp)
            ) {}


            // artists row
            TitleRow("Songs", moreText = "Show more") {

            }

            // songs row
            LazyColumn {
                items(sampleSonsg.size) { index ->
                    HomeSongsItem(index, sampleSonsg[index])
                }
            }
        }
    }
}

@Composable
fun TitleRow(title: String, moreText: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1.copy(fontSize = 14.sp),
            color = MaterialTheme.colors.onSurface
        )
        if (moreText == null) Icon(
            painter = painterResource(id = R.drawable.ic_right),
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