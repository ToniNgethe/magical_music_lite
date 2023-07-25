package com.magicalmusic.core_design.shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magicalmusic.core_design.Ascent
import com.magicalmusic.core_design.MargicalMusicAppTheme
import com.margicalmusic.resources.R


@Composable
fun BottomSheetPermissionContent(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Ascent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_music_folder),
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            stringResource(R.string.permission_music_folder),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 24.sp, color = Color.White
            ),
            modifier = Modifier.padding(4.dp)
        )


        Text(
            text = stringResource(R.string.permission_description),
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.body2.copy(
                color = Color.White, fontSize = 16.sp
            ),
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier.padding(10.dp)) {
            Button(
                onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                )
            ) {
                Text(
                    text = "Grant Access",
                    style = MaterialTheme.typography.h1.copy(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetContentPreview() {
    MargicalMusicAppTheme {
        BottomSheetPermissionContent() {}
    }
}