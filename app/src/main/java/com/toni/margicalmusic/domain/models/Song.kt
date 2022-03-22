package com.toni.margicalmusic.domain.models

import com.toni.margicalmusic.R

data class Song(
    var songArt: Int, var name: String, var artist: Artist
)

var sampleSonsg = listOf(
    Song(
        songArt = R.drawable.sample_image,
        name = "The good music  [ Remix ] ",
        artist = Artist(name = "Polina Tankilevitch", image = R.drawable.sample_image, 2)
    ), Song(
        songArt = R.drawable.sample_image,
        name = "Pexels road - long way home",
        artist = Artist(name = "Nick Casa", image = R.drawable.sample_image, 2)
    ),
    Song(
        songArt = R.drawable.sample_image,
        name = "The good music  [ Remix ] ",
        artist = Artist(name = "Polina Tankilevitch", image = R.drawable.sample_image, 2)
    ),
    Song(
        songArt = R.drawable.sample_image,
        name = "Pexels road - long way home",
        artist = Artist(name = "Nick Casa", image = R.drawable.sample_image, 2)
    ),
    Song(
        songArt = R.drawable.sample_image,
        name = "Pexels road - long way home",
        artist = Artist(name = "Nick Casa", image = R.drawable.sample_image, 2)
    ), Song(
        songArt = R.drawable.sample_image,
        name = "Pexels road - long way home",
        artist = Artist(name = "Nick Casa", image = R.drawable.sample_image, 2)
    )
)