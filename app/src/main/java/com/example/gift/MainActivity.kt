package com.example.gift

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
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
    var oldSong : Song? = null
    //Barra di navigazione
    var isVisible  by remember { mutableStateOf(true) }
    var isRunning  by remember { mutableStateOf(false) }
    isVisible = false
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
                        push(context)
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

            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(0.dp) // Spazio tra gli elementi
            ) {
                itemsIndexed(songs) { index, song ->
                    // Ogni item della lista
                    SongItem(song = song, click = {

                        soundHandler.selectedSong = song

                        //soundHandler.play(soundHandler.mediaPlayer,context)
                        push(context)
                        isRunning = true
                        isVisible = true


                    })

                }
                }


            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                if(isVisible)
                {
                    SongItem(soundHandler.selectedSong, click = {
                        //Apri la sheet view
                        //var intent = Intent(context, Sound(selectedSong)::class.java)
                        //L'utilizzio di una variabile del costruttore non va a buon fine :(
                        var intent = Intent(context, Sound::class.java)
                        intent.putExtra("song", soundHandler.selectedSong)
                        intent.putExtra("state", state)
                        context.startActivity(intent)

                    })


                }
                }


            }

        }


}




@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SongItem(song: Song,click:   () -> Unit) {
    var clicked by remember { mutableStateOf(true) }
    clicked = false
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





@RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StartApp()
}