package com.example.gift

import android.os.Build
import android.os.Bundle
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gift.homeutility.GlobalVariable
import com.example.gift.homeutility.Song
import com.example.gift.ui.theme.GiftTheme
import com.example.gift.homeutility.*


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

@Composable
fun ShowSong(selectedSong: Song?)
{
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

                        Text(text = soundHandler.selectedSong.title,
                            modifier = Modifier
                                .align(Alignment.Start),
                            style = MaterialTheme.typography.displayMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic

                        )


                    }


                }

                //Riga per gestire la musica
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    IconButton(onClick = { /* Azione quando il bottone play viene premuto */ }) {
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



                    IconButton(onClick = { /* Azione quando il bottone play viene premuto */ }) {
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GiftTheme {
        ShowSong(Song("Ciao",getRaw(GlobalVariable.HOME_IMAGE), getRaw(GlobalVariable.HOME_IMAGE) ))
    }
}