package com.margicalmusic.feature_onboarding.presentation.splashscreen

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_navigation.Routes
import com.example.core_navigation.UiEvent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.feature_onboarding.presentation.SplashScreenVm
import com.margicalmusic.resources.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: SplashScreenVm = hiltViewModel(), onNavigate: (UiEvent.OnNavigate) -> Unit
) {
    val isUserOnboarded = viewModel.splashUiState.collectAsState()

    val textVisible = produceState(initialValue = false) {
        delay(1000)
        value = true

        delay(1500)
        onNavigate.invoke(
            UiEvent.OnNavigate(
                if (isUserOnboarded.value.userOnBoarded == true) Routes.HOME_PAGE else Routes.ONBOARDING_SCREEN
            )
        )
    }

    MargicalMusicAppTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
            AnimatedVisibility(visible = textVisible.value) {
                Text(
                    text = "Magical Music App",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(all = 10.dp)
                )
            }
        }
    }
}