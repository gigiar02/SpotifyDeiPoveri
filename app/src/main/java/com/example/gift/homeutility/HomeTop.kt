package com.example.gift.homeutility

import android.content.Context
import android.media.MediaPlayer
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.gift.R

//Variabili globali utilizzate nel progetto
enum class GlobalVariable
{
    HOME_IMAGE;

    //Colori
    enum class color
    {
        BACKGROUND_COLOR,
    }

    enum class icon
    {
        ICON_PLAY,
        ICON_STOP,
        ICON_NEXT,
        ICON_PREVIOUS;

    }

    enum class music
    {
        SENZACUORE,
        GLISBANDATIHANNOPERSO,
        MUSIC_3,
        MUSIC_4
    }
}


//Restituisce la risorsa richiesta
//Modifica queste variabili per ottenere un'esperienza personalizzata
fun getRaw(globalVariable: GlobalVariable) : Int
{
    when(globalVariable) {

        GlobalVariable.HOME_IMAGE -> return R.drawable.prova
        else-> return R.drawable.couple
    }
}


//Restituisce l'id della risorsa mp3 cercato
fun getRaw(globalVariable: GlobalVariable.music) : Int
{
    when(globalVariable)
    {
        GlobalVariable.music.SENZACUORE -> return R.raw.senzacuore
        GlobalVariable.music.GLISBANDATIHANNOPERSO -> return R.raw.glisbandatihannoperso
        else -> return R.raw.senzacuore

    }
}

fun getIcon(globalVariable: GlobalVariable.icon) : Int
{
    when(globalVariable)
    {
        GlobalVariable.icon.ICON_PLAY -> return  android.R.drawable.ic_media_play
        GlobalVariable.icon.ICON_STOP -> return  android.R.drawable.ic_media_pause
        GlobalVariable.icon.ICON_NEXT -> return  android.R.drawable.ic_media_next
        GlobalVariable.icon.ICON_PREVIOUS -> return  android.R.drawable.ic_media_previous
        else -> return  android.R.drawable.ic_media_play
    }


}


fun getColor(globalVariable: GlobalVariable.color) : Color
{
    when(globalVariable)
    {
        GlobalVariable.color.BACKGROUND_COLOR -> return Color(30,31,34)
        else -> return Color(30,31,34)
    }
}

//Canzone
data class Song(
    val title: String,
    val image: Int,
    val music: Int
) : Parcelable {

    // Costruttore che legge dal Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Legge una stringa
        parcel.readInt(),          // Legge un intero (image)
        parcel.readInt()           // Legge un intero (music)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title) // Scrive la stringa nel Parcel
        parcel.writeInt(image)    // Scrive l'intero (image)
        parcel.writeInt(music)    // Scrive l'intero (music)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel) // Usa il costruttore personalizzato
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}


//Restituisce la lista ordinata di canzoni

fun getItem() : List<Song>
{

    val songs = listOf(
        Song(
            "Senza Cuore",
            getRaw(GlobalVariable.HOME_IMAGE),
            getRaw(GlobalVariable.music.SENZACUORE)
        ),
        Song(
            "Gli sbandati hanno perso",
            getRaw(GlobalVariable.HOME_IMAGE),
            getRaw(GlobalVariable.music.GLISBANDATIHANNOPERSO)
        ),
        Song(
            "prova3",
            getRaw(GlobalVariable.HOME_IMAGE),
            getRaw(GlobalVariable.music.GLISBANDATIHANNOPERSO)
        )
    )
    return songs

}



//Gestore musica
class SoundHandler
{
    //Array di canzoni
    var songList = getItem()
    //Gestore musica
    var mediaPlayer by mutableStateOf<MediaPlayer>(MediaPlayer())
    //Canzone selezionata
    var selectedSong by mutableStateOf(songList[0])
    var index by mutableIntStateOf(0)
    var context : Context? = null
    var sliderPosition by mutableStateOf(0f)

    constructor()
    {

        mediaPlayer = MediaPlayer()

    }




    fun SetSelectedSong(song : Song)
    {
        selectedSong = song

    }

}

//A questo punto posso creare il gestore della musica

var soundHandler  by mutableStateOf(SoundHandler())
var lastPosition by mutableStateOf(0)
var oldMediaPlayer by mutableStateOf<MediaPlayer?>(null)
var index by mutableIntStateOf(0)
var oldSong by mutableStateOf<Song?>(null)
//Variabile globale per state

public  var state by mutableStateOf(GlobalVariable.icon.ICON_PLAY)


fun push(context: Context)
{
    if(oldSong != null)
    {
        if(state == GlobalVariable.icon.ICON_STOP)
        {
            if(oldSong == soundHandler.selectedSong)
            {
                state = GlobalVariable.icon.ICON_PLAY
                lastPosition = soundHandler.mediaPlayer.currentPosition
                soundHandler.mediaPlayer.pause()
            }else
            {
                oldSong = soundHandler.selectedSong
                //Smetti di riprodurre la canzone di prima
                oldMediaPlayer?.stop()
                oldMediaPlayer?.release()

                soundHandler.mediaPlayer = MediaPlayer.create(context, soundHandler.selectedSong.music)
                lastPosition = 0;
                soundHandler.mediaPlayer.start()
            }

        }
        else
        {
            if(oldSong == soundHandler.selectedSong)
            {
                state = GlobalVariable.icon.ICON_STOP
                soundHandler.mediaPlayer.seekTo(lastPosition)
                soundHandler.mediaPlayer.start()
            }else
            {
                state = GlobalVariable.icon.ICON_STOP
                oldSong = soundHandler.selectedSong
                oldMediaPlayer?.stop()
                oldMediaPlayer?.release()

                soundHandler.mediaPlayer = MediaPlayer.create(context, soundHandler.selectedSong.music)
                lastPosition = 0;
                soundHandler.mediaPlayer.start()

            }



        }
    }else
    {
        oldSong = soundHandler.selectedSong

        oldMediaPlayer?.stop()
        oldMediaPlayer?.release()

        soundHandler.mediaPlayer = MediaPlayer.create(context, soundHandler.selectedSong.music)
        lastPosition = 0;
        soundHandler.mediaPlayer.start()

    }

    //Riprendi la canzone o fai partire la prima canzone

}


//Inizia nuovamente l'ascolto del brano
fun restartMusic()
{
    //Smetti di riprodurre la musica corrente

    soundHandler.mediaPlayer.seekTo(0);
    soundHandler.sliderPosition = 0f

}

//Vai alla canzone precedente

fun prev()
{
    //Se la canzone precedente non esiste
    state = GlobalVariable.icon.ICON_STOP
    if(index == 0)
    {
        restartMusic()
        return;
    }

    //Modifica la canzone corrente
    index--;
    soundHandler.selectedSong = soundHandler.songList[index]
    oldMediaPlayer?.stop()
    oldMediaPlayer?.release()
    soundHandler.mediaPlayer = MediaPlayer.create(soundHandler.context, soundHandler.selectedSong.music)
    oldMediaPlayer = soundHandler.mediaPlayer
    soundHandler.mediaPlayer.start()

}


//Passa alla canzone successiva
fun next()
{
    //Se la canzone precedente non esiste
    if(index == soundHandler.songList.size-1)
    {
        restartMusic()
        //Commento
        return;
    }

    //Modifica la canzone corrente
    index++;
    soundHandler.selectedSong = soundHandler.songList[index]
    oldSong = soundHandler.selectedSong
    oldMediaPlayer?.stop()
    oldMediaPlayer?.release()
    soundHandler.mediaPlayer = MediaPlayer.create(soundHandler.context, soundHandler.selectedSong.music)
    oldMediaPlayer = soundHandler.mediaPlayer
    state = GlobalVariable.icon.ICON_STOP
    soundHandler.mediaPlayer.start()

}


