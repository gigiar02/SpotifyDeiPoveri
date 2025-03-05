package com.example.gift.homeutility

import android.media.MediaPlayer
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
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
        MUSIC_1,
        MUSIC_2,
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
        GlobalVariable.music.MUSIC_1 -> return R.drawable.couple
        GlobalVariable.music.MUSIC_2 -> return R.drawable.couple
        else -> return R.drawable.couple

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

//Gestore musica
class soundHandler
{
    //Array di canzoni
    var songList : List<Song>
    //Gestore musica
    var mediaPlayer : MediaPlayer

    constructor(song : List<Song>, media : MediaPlayer)
    {
        songList = song
        mediaPlayer = media

    }

    fun addSong(song : Song)
    {
        

    }
}
//Restituisce la lista ordinata di canzoni
@Composable
fun getItem() : List<Song>
{

    val songs = remember { mutableStateListOf(
            Song("prova", getRaw(GlobalVariable.HOME_IMAGE), getRaw(GlobalVariable.music.MUSIC_1)),
            Song("prova2", getRaw(GlobalVariable.HOME_IMAGE), getRaw(GlobalVariable.music.MUSIC_2)),
            Song("prova3", getRaw(GlobalVariable.HOME_IMAGE), getRaw(GlobalVariable.music.MUSIC_3))
            ) }
    return songs

}


