package com.toni.margicalmusic.domain.models

import com.toni.margicalmusic.R

data class Artist(val name: String, var image: Int, var noTracks: Int)

val sampleArtistsData = listOf(
    Artist(name = "Polina Tankilevitch", image = R.drawable.sample_image, 2),
    Artist(name = "Nick Casa", image = R.drawable.sample_image, 21),
    Artist(name = "Jamiesx", image = R.drawable.sample_image, 21),
    Artist(name = "Polina Tankilevitch", image = R.drawable.sample_image, 2),
)