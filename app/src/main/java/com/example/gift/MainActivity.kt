package com.example.gift

import android.content.Intent
import android.graphics.ImageDecoder
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gift.ui.theme.GiftTheme
import com.example.gift.homeutility.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GiftTheme {
                StartApp()

            }
        }
    }
}

@Composable
fun yes()
{
    var context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text("Hello")
        Button(onClick = {
            val intent = Intent(context, Sound::class.java)
            context.startActivity(intent)
        }) {
            Text("Apri Sound Activity")
        }
        //StartApp()
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
@Composable
fun StartApp()
{
    val context = LocalContext.current
    soundHandler.context = context
    //Barra di navigazione
    var isVisible  by remember { mutableStateOf(false) }
    var isRunning  by remember { mutableStateOf(false) }

    val songs = soundHandler.songList
    val handler = soundHandler
    var selection  = soundHandler.selectedSong
    var currentIndex  by remember { mutableStateOf(0) }

    Scaffold(modifier = Modifier.fillMaxSize(),containerColor = getColor(GlobalVariable.color.BACKGROUND_COLOR)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = getRaw(GlobalVariable.HOME_IMAGE)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.FillWidth
            )

            //Bottone per avviare la musica
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(onClick = {
                    //Se lo stato era play e hai cliccato
                    if (state == GlobalVariable.icon.ICON_PLAY) {
                        state = GlobalVariable.icon.ICON_STOP

                        //Devi riprendere la riproduzione da dove eri
                        if (oldSong != null) {
                            //Si posiziona all'ultimo punto in cui si era fermato
                            soundHandler.mediaPlayer.seekTo(lastPosition)
                            //Inizia la riproduzione
                            soundHandler.mediaPlayer.start()
                        } else {
                            //Questa è la prima canzone ad essere riprodotta
                            //Imposta l'index a 0 per poter partire dalla prima
                            index = 0
                            //Aggiorna la canzone piu recente
                            oldSong = soundHandler.songList[index]
                            //La canzone selezionata sarà la prima
                            soundHandler.selectedSong = soundHandler.songList[index]
                            //Inizia la riproduzione
                            oldMediaPlayer =
                                MediaPlayer.create(context, soundHandler.selectedSong.music)
                            soundHandler.mediaPlayer =
                                MediaPlayer.create(context, soundHandler.selectedSong.music)
                            lastPosition = 0;
                            soundHandler.mediaPlayer.start()
                        }


                    } else {
                        //Quando il tasto è stato premuto si stava riproducento la musica che va messa in pausa
                        lastPosition = soundHandler.mediaPlayer.currentPosition
                        soundHandler.mediaPlayer.pause()
                        state = GlobalVariable.icon.ICON_PLAY


                    }
                    isVisible = true


                }) {
                    Icon(
                        painter = painterResource(id = getIcon(state)), // Icona play di Android
                        contentDescription = "Play",
                        tint = Color.Red // Colore dell'icona
                    )
                }
            }


            //Lista di canzoni
            Box(

            )
            {
            LazyColumn(
                modifier = Modifier.wrapContentSize()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(0.dp) // Spazio tra gli elementi

            ) {
                itemsIndexed(songs) { i, song ->
                    // Ogni item della lista
                    SongItem(song = song, click = {
                        //Assegno l'index della canzone
                        state = GlobalVariable.icon.ICON_STOP
                        oldMediaPlayer?.stop()
                        oldMediaPlayer?.release()

                        //Aggiorno la canzone piu recente, l'index e il posizionamento
                        soundHandler.selectedSong = song
                        soundHandler.mediaPlayer =
                            MediaPlayer.create(context, soundHandler.selectedSong.music)
                        index = i
                        lastPosition = 0

                        //Aggiorna la canzone come la piu recente
                        oldSong = song
                        oldMediaPlayer = soundHandler.mediaPlayer

                        //Inizia la riproduzione
                        soundHandler.mediaPlayer.start()
                        isVisible = true


                    })

                }
            }
                if (isVisible) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
                    .height(70.dp)
                    .offset(x = 0.dp, y = 530.dp)
                    .background(Color.Black)
                ,
                //horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {

                    SongBoard(click = {
                        //Apri la sheet view
                        //var intent = Intent(context, Sound(selectedSong)::class.java)
                        //L'utilizzio di una variabile del costruttore non va a buon fine :(
                        var intent = Intent(context, Sound::class.java)
                        context.startActivity(intent)

                    })



            }
                }
            }
            soundHandler.mediaPlayer.setOnCompletionListener {
                if(index == soundHandler.songList.size-1)
                {
                    index = -1
                }
                next()
            }





            }

        }


}




@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SongItem(song: Song,click:   () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable (onClick = click),

        horizontalArrangement = Arrangement.spacedBy(15.dp),

    ) {

        Image(
            painter = painterResource(id = song.image),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Fit

        )

        Text(
            song.title,
            color = Color.White,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically),
            style = MaterialTheme.typography.headlineMedium

        )

    }


}



@Composable
fun SongBoard(click:   () -> Unit) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable (onClick = click),

            horizontalArrangement = Arrangement.spacedBy(15.dp),

            ) {

            Image(
                painter = painterResource(id = soundHandler.selectedSong.image),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Fit

            )

            Text(
                soundHandler.selectedSong.title,
                color = Color.White,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                style = MaterialTheme.typography.headlineMedium

            )

        }




}





@RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartApp()
}