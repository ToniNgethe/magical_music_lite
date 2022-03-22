package com.toni.margicalmusic.domain.models

import androidx.compose.ui.graphics.Color
import com.toni.margicalmusic.R

data class Category(
    var title: String, var image: Int, var color: Color
)

val sampleDaya = listOf(
    Category(title = "Hip hop", image = R.drawable.sample_image, color = Color(0xFF84A59D)),
    Category(title = "Rnb", image = R.drawable.sample_image, color = Color(0xFFE55C57)),
    Category(title = "Hip hop/Rap", image = R.drawable.sample_image, color = Color(0xFFF6BD60)),
    Category(title = "Soft beat", image = R.drawable.sample_image, color = Color(0xFFE55C57)),

    )