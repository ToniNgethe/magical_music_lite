package com.toni.margicalmusic.ui.presentation.on_boarding.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingItem(title: String, icon: Int) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "music logo",
            modifier = Modifier.padding(10.dp),
            tint = MaterialTheme.colors.onSurface
        )
        Text(
            text = title, color = MaterialTheme.colors.onSurface, modifier = Modifier.padding(10.dp)
        )
    }
}