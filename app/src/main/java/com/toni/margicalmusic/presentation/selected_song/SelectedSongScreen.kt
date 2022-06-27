package com.toni.margicalmusic.presentation.selected_song

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.toni.margicalmusic.R
import com.toni.margicalmusic.presentation.theme.MargicalMusicAppTheme
import com.toni.margicalmusic.presentation.ui.utils.UiEvent

@Composable
fun SelectedSongScreen(
    context: Context,
    lifecycle: Lifecycle,
    navController: NavHostController,
    onNavigate: (UiEvent.OnNavigate) -> Unit,
) {

    MargicalMusicAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 18.dp)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close page",
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Text(
                        text = "Song title goes here",
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                val youtubePlayer = remember {
                    YouTubePlayerView(context).apply {
                        lifecycle.addObserver(this)
                        enableAutomaticInitialization = false
                        initialize(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo("nlMYTD_pHBA", 0f)
                            }
                        })
                    }
                }
                AndroidView(
                    {
                        youtubePlayer
                    }, modifier = Modifier
                        .padding(all = 5.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

                Text(
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    text = "[Intro: Drake]\n" + "Just awaken shaken once again, so you know it's on\n" + "Just awaken shaken once again, ho, you know it's on\n" + "Just awaken shaken once again, so you know it's on\n" + "Just awaken shaken once again, ho, you know it's on\n" + "Yeah, life\n" + "Life is the only thing we need\n" + "They need me to go, but I don't wanna leave\n" + "Rest in peace to Lil Keed\n" + "\n" + "[Verse 1: Drake]\n" + "Fuck a pigeonhole, I'm a night owl, this a different mode\n" + "I might have to make her paint a 6 on her pinky toe\n" + "Heard you with a shooting guard, just let a nigga know\n" + "I would have you courtside, not the middle row\n" + "All good, love, in a minute, though\n" + "I can't strеss about no bitch 'cause I'm a timid soul\n" + "Plus I'm cookin' up ambition on a kitchen stove\n" + "Pot start to bubblе, see the suds, that shit good to go\n" + "Hoes say I'm suave, but I can't get RICO'd\n" + "Bro think he John Wayne, I bought him yellow stones\n" + "Love the way they hang, babe, fuck the silicone\n" + "Everybody fake now, you could crack the code\n" + "Bustdown everything, set in rose gold\n" + "Dread talkin' to you niggas like I'm J. Cole\n" + "I can tell her head good before I even know\n" + "Bitch, don't tell me that you model if you ain't been in Vogue\n" + "Gotta throw a party for my day ones\n" + "They ain't in the studio, but they'll lay somethin'\n" + "Rest in peace to Drama King, we was straight stuntin'\n" + "You don't like the way I talk? Nigga, say somethin'\n" + "Gotta throw a party for my day ones\n" + "Pull up and you know it's us, the bass jumpin'\n" + "You don't like the way I talk? Then say somethin'\n" + "Get out my face, nigga\n" + "[Chorus: Drake & 21 Savage]\n" + "Gotta throw a party for my day ones\n" + "They ain't in the studio, but they'll lay somethin'\n" + "Rest in peace to Drama King, we was straight stuntin'\n" + "If I let my nigga 21 tell it, you a\n" + "Pussy\n" + "\n" + "[Verse 2: 21 Savage]\n" + "Spin a block twice like it ain't nowhere to park (21)\n" + "Smack the backside of his head like he Bart (Pussy)\n" + "OVO 4L, we come out when it get dark (21, 21)\n" + "Big stepper, he came in a Rolls, but he left in a stretcher (21)\n" + "Let my brother drive while I shoot, team effort (21)\n" + "Askin' all these questions, bitch, you must think you Nadeska\n" + "The chopper like to feel on all the opps, it's a molester (21)\n" + "I be with my gun like Rozay be with lemon pepper\n" + "She wanna hear some Afrobeats 'cause she just popped a Tesla\n" + "All that workin' out, that nigga must think he a wrestler\n" + "But this ain't UFC, this chopper came with a compressor (21)\n" + "This chopper came with a compressor (Pussy)\n" + "This chopper came with a— (Pussy)\n" + "This Glock 45 came with a switch (21)\n" + "If I was Will Smith, I would've slapped him with a stick\n" + "Put your hands in the air, it's a stick-up (21)\n" + "Spin the same hood where I get my dick sucked (Facts)\n" + "If you standin' on business, put your blick up (21, 21)\n" + "Come around actin' scary, get your shit took (21)\n" + "Fell in love with feelin' dizzy, so I spizzin (21)\n" + "I got mad love for the boy, yeah, that's my twizzin (21)\n" + "If them niggas keep on dissin', slide agaizzin (21)\n" + "We the reason why the opps ain't got no frizziends (21)\n" + "Last nigga played with me got turned duppy\n" + "I ain't even roll him in the 'Wood 'cause he musty\n" + "You ask how she doin', I just tell her come and fuck me\n" + "Shot his ass twenty times, damn, this nigga lucky (Damn, that nigga lucky)\n" + "[Chorus: Drake]\n" + "Gotta throw a party for my day ones\n" + "They ain't in the studio, but they'll lay somethin'\n" + "Rest in peace to Drama King, we was straight stuntin'\n" + "You don't like the way I talk? Nigga, say somethin'\n" + "\n" + "[Outro: Drake]\n" + "Say somethin', say somethin', say somethin', say somethin', say somethin'\n" + "You don't like the way I talk? Nigga, say somethin', say somethin', say somethin', say somethin', say somethin'"
                )


            }
        }
    }
}
