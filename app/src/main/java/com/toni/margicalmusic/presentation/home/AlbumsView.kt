package com.toni.margicalmusic.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.magicalmusic.core_design.shared_components.HomePageHeader
import com.toni.margicalmusic.presentation.ui.CustomSearchField

@Composable
fun AlbumsView() {
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            HomePageHeader() {

            }
            CustomSearchField {

            }


        }
    }
}