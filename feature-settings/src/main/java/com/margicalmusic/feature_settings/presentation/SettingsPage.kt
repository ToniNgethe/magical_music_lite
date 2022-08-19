package com.margicalmusic.feature_settings.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.magicalmusic.core_design.MargicalMusicAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsPage(
    viewModel: SettingsVm = hiltViewModel(), onNavigate: NavHostController
) {
    val uiState = viewModel.settingsUiState.collectAsState()

    val theme = when (AppCompatDelegate.getDefaultNightMode()) {
        AppCompatDelegate.MODE_NIGHT_NO -> false
        AppCompatDelegate.MODE_NIGHT_YES -> true
        else -> isSystemInDarkTheme()
    }

    val checkedState = remember { mutableStateOf(theme) }

    LaunchedEffect(key1 = checkedState) {
        checkedState.value = uiState.value.isDarkMode
    }

    MargicalMusicAppTheme {
        Scaffold(topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { onNavigate.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }, elevation = 0.dp, title = {
                Text(
                    text = "Settings", style = MaterialTheme.typography.h1.copy(
                        fontSize = 20.sp, color = MaterialTheme.colors.secondary
                    )
                )
            }, backgroundColor = Color.Transparent
            )
        }, content = {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.padding(18.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Night Mode",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 22.sp,
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = if (uiState.value.isDarkMode) "On" else "off",
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                        Switch(checked = checkedState.value, onCheckedChange = {
                            viewModel.toggleTheme()
                            checkedState.value = !checkedState.value
                        })
                    }
                }

            }
        })
    }
}