package com.toni.margicalmusic.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomePage() {
    Scaffold {
        Column {
            Text(text = "Welcome to margical music")
        }
    }
}