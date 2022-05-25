package com.toni.margicalmusic.presentation.home_page.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.toni.margicalmusic.R
import com.toni.margicalmusic.utils.MediaUtils.getAlbumArtUri

@Composable
fun CategoriesView(title: String, imageUri: Uri, color: Color) {
    Box(modifier = Modifier.padding(start = 10.dp)) {
        Row(
            modifier = Modifier.background(color = color),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = imageUri),
                contentScale = ContentScale.FillBounds,
                contentDescription = "image",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = title,
                modifier = Modifier.padding(all = 5.dp),
                style = MaterialTheme.typography.h2.copy(
                    color = Color.White, fontSize = 14.sp
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(all = 5.dp)
            )
        }
    }
}