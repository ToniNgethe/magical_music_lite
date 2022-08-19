package com.toni.margicalmusic.presentation.shared_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_navigation.Routes
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.Ascent
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomePageHeader(onNavigate: ((UiEvent.OnNavigate) -> Unit)) {
    val greetings = remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        greetings.value = when (hour.toInt()) {
            in 12..16 -> {
                "Good Afternoon"
            }
            in 17..20 -> {
                "Good Evening"
            }
            in 21..23 -> {
                "Good Night"
            }
            else -> {
                "Good Morning"
            }
        }
    }

    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = greetings.value,
            color = Ascent,
            style = MaterialTheme.typography.h1.copy(fontSize = 24.sp)
        )

        IconButton(onClick = {
            onNavigate.invoke(UiEvent.OnNavigate(Routes.SETTINGS_PAGE))
        }) {
            Icon(
                painter = painterResource(id = com.margicalmusic.resources.R.drawable.ic_settings),
                contentDescription = "settings",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}