package com.example.gift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gift.homeutility.GlobalVariable
import com.example.gift.homeutility.Song
import com.example.gift.homeutility.getRaw
import com.example.gift.ui.theme.GiftTheme
import kotlinx.coroutines.flow.StateFlow

class Sound(private val selectedSong: Song) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GiftTheme {

                ShowSong(selectedSong)
            }
        }
    }
}

@Composable
fun ShowSong(selectedSong: Song)
{
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

            ){
                Image(
                    painter = painterResource(id = selectedSong.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(text = selectedSong.title,
                    modifier = Modifier
                        .align(Alignment.Start),
                    style = MaterialTheme.typography.headlineMedium
                )

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