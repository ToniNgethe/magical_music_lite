package com.toni.margicalmusic.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.toni.margicalmusic.presentation.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.ui.CustomSearchField

@Composable
fun AlbumsView() {
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            HomePageHeader()
            CustomSearchField()


        }
    }
}