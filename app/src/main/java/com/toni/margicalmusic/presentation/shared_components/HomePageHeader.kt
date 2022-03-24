package com.toni.margicalmusic.presentation.shared_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.theme.Ascent

@Composable
fun HomePageHeader(displaySearchIcon: Boolean = false) {
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
        if (displaySearchIcon) Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "search",
            tint = MaterialTheme.colors.onSurface
        )
    }
}