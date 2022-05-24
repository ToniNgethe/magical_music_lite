package com.toni.margicalmusic.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.shared_components.HomeSongsItem
import com.toni.margicalmusic.presentation.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.theme.gray_a

@Composable
fun SongsView() {
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            HomePageHeader()

            // search
            val text = remember {
                mutableStateOf("")
            }
            Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primaryVariant),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            modifier = Modifier.padding(all = 10.dp),
                            tint = gray_a
                        )
                        BasicTextField(
                            value = text.value,
                            onValueChange = {
                                text.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            textStyle = TextStyle.Default.copy(color = MaterialTheme.colors.onSurface)
                        )
                    }
                    if (text.value.isEmpty()) Text(
                        color = gray_a, text = "Search", modifier = Modifier.padding(start = 50.dp)
                    )
                }
            }

            // songs row
//            LazyColumn {
//                items(sampleSonsg.size) { index ->
//                    HomeSongsItem(index, sampleSonsg[index], icon = R.drawable.ic_play)
//                }
//            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark theme")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "white theme")
@Composable
fun SongsViewPreview() {
    SongsView()
}