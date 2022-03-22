package com.toni.margicalmusic.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.ui.utils.Routes
import com.toni.margicalmusic.ui.utils.UiEvent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen( onNavigate: ( UiEvent.OnNavigate ) -> Unit ) {
    val textVisible = produceState(initialValue = false) {
        delay(1000)
        value = true

        delay( 1500 )
        onNavigate.invoke(UiEvent.OnNavigate( Routes.ONBOARDING_SCREEN ))
    }
    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = "logo")
            AnimatedVisibility(visible = textVisible.value) {
                Text(
                    text = "Margical Music App",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(all = 10.dp)
                )
            }
        }
    }
}