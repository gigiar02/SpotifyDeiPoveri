package com.example.gift.homeutility

import android.media.MediaPlayer
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

    constructor()
    {

        mediaPlayer = MediaPlayer()

    }




    fun SetSelectedSong(song : Song)
    {
        selectedSong = song

    }

    @Composable
    fun play(oldMediaPlayer: MediaPlayer?) : MediaPlayer
    {
        val playSound by remember { mutableStateOf(false) }
        //Prendo il contesto corrente
        val context = LocalContext.current
        //Ottieni il file mp3 del suono randomico scelto
        val ID = selectedSong.music

        //Ferma  e rilascia l'ex mediaplayer se esiste
        oldMediaPlayer?.stop()
        oldMediaPlayer?.release()

        mediaPlayer = remember { MediaPlayer.create(context,ID)}

        LaunchedEffect(Unit) {


            mediaPlayer.start()
        }



        return mediaPlayer
    }

}

//A questo punto posso creare il gestore della musica

var soundHandler  by mutableStateOf(SoundHandler())


fun getSoundHandler() : SoundHandler
{
    return soundHandler
}


//Variabile globale per state

public  var state by mutableStateOf(GlobalVariable.icon.ICON_PLAY)


fun getState() : GlobalVariable.icon
{
    return state
}


fun push()
{
    if(state == GlobalVariable.icon.ICON_STOP)
    {
        state = GlobalVariable.icon.ICON_PLAY

    }
    else
    {
        state = GlobalVariable.icon.ICON_STOP
    }

    //Riprendi la canzone o fai partire la prima canzone

}


