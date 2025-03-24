package com.example.gift

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getDrawable
import com.example.gift.homeutility.GlobalVariable
import com.example.gift.homeutility.Song
import com.example.gift.ui.theme.GiftTheme
import com.example.gift.homeutility.*
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.delay






class Sound() : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GiftTheme {

                ShowSong(soundHandler.selectedSong)


            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ShowSong(selectedSong: Song?)
{
    var conf = LocalConfiguration.current
    LaunchedEffect(Unit) {
        while (true) {
            delay(20L) // Velocità del movimento
            soundHandler.position += 2f
            soundHandler.gifPosition = soundHandler.position - soundHandler.selectedSong.distance
            if (soundHandler.position > conf.screenWidthDp + soundHandler.selectedSong.distance) soundHandler.position = (-conf.screenWidthDp + soundHandler.selectedSong.distance) + 0f // Reset quando esce dallo schermo
        }
    }

    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = getColor(GlobalVariable.color.BACKGROUND_COLOR) ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ){
                    val mediaUrl = Uri.parse("https://tenor.com/it/view/bubu-running-cute-gif-8641804800116646283")
                    if(selectedSong != null)
                    {
                        Image(
                            painter = painterResource(id = soundHandler.selectedSong.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(350.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop
                        )

                            var Context = soundHandler.context
                            val animatedDrawable = remember {
                                val source = ImageDecoder.createSource(context.resources,
                                    soundHandler.selectedSong.gif)
                                ImageDecoder.decodeDrawable(source).apply {
                                    if (this is AnimatedImageDrawable) {
                                        setRepeatCount(AnimatedImageDrawable.REPEAT_INFINITE) // Loop infinito
                                    }
                                }
                            }

                            Row(
                                    verticalAlignment = Alignment.CenterVertically,
                            )
                            {
                                Image(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .offset(x = soundHandler.gifPosition.dp),   //crops the image to circle shape
                                    painter = rememberDrawablePainter(animatedDrawable),
                                    contentDescription = "Loading animation",

                                    )

                                Text(text = soundHandler.selectedSong.title,
                                    modifier = Modifier
                                        .offset(x = soundHandler.position.dp),

                                    style = MaterialTheme.typography.headlineMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,

                                )
                            }



                    }


                    LaunchedEffect(soundHandler.mediaPlayer) {
                        while (true) {
                            delay(200L) // Aggiornamento ogni 200ms
                            if (soundHandler.mediaPlayer.isPlaying) {
                                soundHandler.sliderPosition = soundHandler.mediaPlayer.currentPosition / soundHandler.mediaPlayer.duration.toFloat()
                            }
                        }
                    }


                    Slider(
                        value = soundHandler.sliderPosition,
                        //100000
                        onValueChange = {

                                          soundHandler.mediaPlayer.seekTo((it* soundHandler.mediaPlayer.duration).toInt())
                                          soundHandler.sliderPosition = it
                                        },
                        modifier = Modifier.width(350.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Red, // Colore del pallino
                            activeTrackColor = Color.Blue, // Colore della barra attiva
                            inactiveTrackColor = Color.Gray, // Colore della barra inattiva
                        )
                    )
                    Text(text =  soundHandler.sliderPosition.toString())


                }

                //Riga per gestire la musica
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    IconButton(onClick = { prev() }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_previous),
                            modifier = Modifier
                                .size(100.dp),// Icona play di Android
                            contentDescription = "Play",
                            tint = Color.Red

                        )


                    }

                        IconButton(onClick = {
                            push(context = context)
                        }) {
                            Icon(
                                painter = painterResource(id = getIcon(state)), // Icona play di Android
                                contentDescription = "Play",
                                tint = Color.Red // Colore dell'icona
                            )


                        }



                    IconButton(onClick = {next() }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_next), // Icona play di Android
                            contentDescription = "Play",
                            tint = Color.Red,
                            modifier = Modifier
                                .width(300.dp)

                        )


                    }






                }

            }





        }
    }
}




@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GiftTheme {
        ShowSong(Song("Ciao",getRaw(GlobalVariable.HOME_IMAGE), getRaw(GlobalVariable.HOME_IMAGE),0,0 ))
    }
}
