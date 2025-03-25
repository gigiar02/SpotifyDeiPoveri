package com.example.gift.homeutility

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.gift.R
import java.net.URI

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
        OKANE,
        DIVA,
        MAMASBOY,
        SOLDIER,
        WRONG,
        FALLINGDOWN,
        LALA,
        DARKRED,
        AFFIRMATION,
        APT,
        WASHINGMACHINE,
        MEANTHEDEVIL,
        ALONEATTHEEDGE,
        LAYALLYOURLOVEONME,
        ALLQUIETONONTHEWESTERN,
        DARKISTHENIGHT,
        IDONTWANTTOSET,
        TIFASTAREBENE,
        ANXIETY
    }

    enum class gif
    {
        RUNNING,
        DUDUBUBU,
        DUDUBUBU1,
        DUDU1,
        DUDU2,
        DUDU3,
        DUDU4,
        DUDU9,
        DUDU10,
        DUDU11,
        DUDU12,
        DUDU13,
        DUDU14,
        HAPPY,
        HAPPY2,
        HAPPY3,
        HAPPY4,
        HAPPY5,
        HAPPY7,
        SFOTTI,
        ANGRY,
        FUNNY,
        BED
    }

    enum class  img
    {
        MANU1,
        MANU2,
        MANU3,
        MANU4,
        MANU5,
        MANU6,
        MANU7,
        MANU8,
        MANU9,
        MANU10,
        MANU11,
        MANU12,
        MANU13,
        MANU14,
        MANU15,
        MANU16,
        MANU17,
        MANU18,
        MANU19,
        MANU20,
        MANU21
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
        GlobalVariable.music.OKANE -> return R.raw.okane
        GlobalVariable.music.DIVA -> return R.raw.diva
        GlobalVariable.music.MAMASBOY -> return R.raw.mamasboy
        GlobalVariable.music.SOLDIER -> return R.raw.soldier
        GlobalVariable.music.WRONG -> return R.raw.wrong
        GlobalVariable.music.FALLINGDOWN -> return R.raw.fallingdown
        GlobalVariable.music.LALA -> return R.raw.lala
        GlobalVariable.music.DARKRED -> return R.raw.darkred
        GlobalVariable.music.AFFIRMATION -> return R.raw.affirmation
        GlobalVariable.music.APT -> return R.raw.apt
        GlobalVariable.music.WASHINGMACHINE -> return R.raw.washingmachine
        GlobalVariable.music.MEANTHEDEVIL -> return R.raw.meandthedevil
        GlobalVariable.music.ALONEATTHEEDGE -> return R.raw.aloneattheedge
        GlobalVariable.music.LAYALLYOURLOVEONME -> return R.raw.layallyourloveonme
        GlobalVariable.music.ALLQUIETONONTHEWESTERN -> return R.raw.allquietonthewestern
        GlobalVariable.music.DARKISTHENIGHT -> return R.raw.darkisthenight
        GlobalVariable.music.IDONTWANTTOSET -> return R.raw.idontwanttoset
        GlobalVariable.music.TIFASTAREBENE -> return R.raw.tifastarebene
        GlobalVariable.music.ANXIETY -> return R.raw.anxiety
        else -> return R.raw.senzacuore

    }
}


fun getRaw(globalVariable: GlobalVariable.gif) : Int
{
    when(globalVariable)
    {
        GlobalVariable.gif.RUNNING -> return R.drawable.running
        GlobalVariable.gif.DUDUBUBU -> return R.drawable.dudububu
        GlobalVariable.gif.DUDU1 -> return R.drawable.dudu
        GlobalVariable.gif.DUDU2 -> return R.drawable.dudu2
        GlobalVariable.gif.DUDU3 -> return R.drawable.dudu3
        GlobalVariable.gif.DUDU4 -> return R.drawable.dudu4
        GlobalVariable.gif.DUDU9 -> return R.drawable.dudu9
        GlobalVariable.gif.DUDU10 -> return R.drawable.dudu10
        GlobalVariable.gif.DUDU11 -> return R.drawable.dudu11
        GlobalVariable.gif.DUDU12 -> return R.drawable.dudu12
        GlobalVariable.gif.DUDU13 -> return R.drawable.dudu13
        GlobalVariable.gif.DUDU14 -> return R.drawable.dudu14
        GlobalVariable.gif.HAPPY -> return R.drawable.happy
        GlobalVariable.gif.HAPPY2 -> return R.drawable.happy2
        GlobalVariable.gif.HAPPY3 -> return R.drawable.happy3
        GlobalVariable.gif.HAPPY4 -> return R.drawable.happy4
        GlobalVariable.gif.HAPPY5 -> return R.drawable.happy5
        GlobalVariable.gif.HAPPY7 -> return R.drawable.happy7
        GlobalVariable.gif.SFOTTI -> return R.drawable.sfotti
        GlobalVariable.gif.ANGRY -> return R.drawable.angry
        GlobalVariable.gif.FUNNY -> return R.drawable.funny
        GlobalVariable.gif.BED -> return R.drawable.bed
        GlobalVariable.gif.DUDUBUBU1 -> return R.drawable.dudububu1

        else -> return R.drawable.running

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

fun getRaw(globalVariable: GlobalVariable.img) : Int
{
    when(globalVariable)
    {
        GlobalVariable.img.MANU1 -> return R.drawable.manu1
        GlobalVariable.img.MANU2 -> return R.drawable.manu2
        GlobalVariable.img.MANU3 -> return R.drawable.manu3
        GlobalVariable.img.MANU4 -> return R.drawable.manu4
        GlobalVariable.img.MANU5 -> return R.drawable.manu5
        GlobalVariable.img.MANU6 -> return R.drawable.manu6
        GlobalVariable.img.MANU7 -> return R.drawable.manu7
        GlobalVariable.img.MANU8 -> return R.drawable.manu8
        GlobalVariable.img.MANU9 -> return R.drawable.manu9
        GlobalVariable.img.MANU10 -> return R.drawable.manu10
        GlobalVariable.img.MANU11 -> return R.drawable.manu11
        GlobalVariable.img.MANU12 -> return R.drawable.manu12
        GlobalVariable.img.MANU13 -> return R.drawable.manu13
        GlobalVariable.img.MANU14 -> return R.drawable.manu14
        GlobalVariable.img.MANU15 -> return R.drawable.manu15
        GlobalVariable.img.MANU16 -> return R.drawable.manu16
        GlobalVariable.img.MANU17 -> return R.drawable.manu17
        GlobalVariable.img.MANU18 -> return R.drawable.manu18
        GlobalVariable.img.MANU19 -> return R.drawable.manu19
        GlobalVariable.img.MANU20 -> return R.drawable.manu20


        else -> return R.drawable.manu1


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
    val music: Int,
    val gif: Int,
    val distance: Int
) : Parcelable {

    // Costruttore che legge dal Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", // Legge una stringa
        parcel.readInt(),          // Legge un intero (image)
        parcel.readInt(),           // Legge un intero (music)
        parcel.readInt(),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title) // Scrive la stringa nel Parcel
        parcel.writeInt(image)    // Scrive l'intero (image)
        parcel.writeInt(music)    // Scrive l'intero (music)
        parcel.writeInt(gif)
        parcel.writeInt(distance)
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
            getRaw(GlobalVariable.img.MANU1),
            getRaw(GlobalVariable.music.SENZACUORE),
            getRaw(GlobalVariable.gif.RUNNING),
            50
        ),
        Song(
            "Gli sbandati hanno perso",
            getRaw(GlobalVariable.img.MANU2),
            getRaw(GlobalVariable.music.GLISBANDATIHANNOPERSO),
            getRaw(GlobalVariable.gif.BED),
            100
        ),
        Song(
            "Mamushi",
            getRaw(GlobalVariable.img.MANU3),
            getRaw(GlobalVariable.music.OKANE),
            getRaw(GlobalVariable.gif.DUDU10),
            50
        ),
        Song(
            "Diva",
            getRaw(GlobalVariable.img.MANU4),
            getRaw(GlobalVariable.music.DIVA),
            getRaw(GlobalVariable.gif.DUDU11),
            50
        ),
        Song(
            "Mama's Boy",
            getRaw(GlobalVariable.img.MANU5),
            getRaw(GlobalVariable.music.MAMASBOY),
            getRaw(GlobalVariable.gif.DUDU12),
            50
        ),
        Song(
            "Soldier",
            getRaw(GlobalVariable.img.MANU6),
            getRaw(GlobalVariable.music.SOLDIER),
            getRaw(GlobalVariable.gif.DUDU12),
            50
        ),
        Song(
                "Wrong",
        getRaw(GlobalVariable.img.MANU7),
        getRaw(GlobalVariable.music.WRONG),
        getRaw(GlobalVariable.gif.BED),
        50
        ),Song(
            "Falling Down",
            getRaw(GlobalVariable.img.MANU8),
            getRaw(GlobalVariable.music.FALLINGDOWN),
            getRaw(GlobalVariable.gif.DUDU13),
            50
        ),
        Song(
            "LA LA LA",
            getRaw(GlobalVariable.img.MANU9),
            getRaw(GlobalVariable.music.LALA),
            getRaw(GlobalVariable.gif.DUDU2),
            50
        ),
        Song(
            "Dark Red",
            getRaw(GlobalVariable.img.MANU10),
            getRaw(GlobalVariable.music.DARKRED),
            getRaw(GlobalVariable.gif.DUDU1),
            50
        ),
        Song(
            "Affirmation",
            getRaw(GlobalVariable.img.MANU11),
            getRaw(GlobalVariable.music.AFFIRMATION),
            getRaw(GlobalVariable.gif.SFOTTI),
            50
        ),
        Song(
            "Washing Machine",
            getRaw(GlobalVariable.img.MANU12),
            getRaw(GlobalVariable.music.WASHINGMACHINE),
            getRaw(GlobalVariable.gif.HAPPY7),
            50
        ),
        Song(
            "APT",
            getRaw(GlobalVariable.img.MANU13),
            getRaw(GlobalVariable.music.APT),
            getRaw(GlobalVariable.gif.HAPPY4),
            50
        ),
        Song(
            "Me And The Devil",
            getRaw(GlobalVariable.img.MANU14),
            getRaw(GlobalVariable.music.MEANTHEDEVIL),
            getRaw(GlobalVariable.gif.HAPPY3),
            50
        ),
        Song(
            "Alone At The Edge",
            getRaw(GlobalVariable.img.MANU15),
            getRaw(GlobalVariable.music.ALONEATTHEEDGE),
            getRaw(GlobalVariable.gif.HAPPY2),
            50
        ),
        Song(
            "Lay All Your Love On Me",
            getRaw(GlobalVariable.img.MANU16),
            getRaw(GlobalVariable.music.LAYALLYOURLOVEONME),
            getRaw(GlobalVariable.gif.HAPPY),
            150
        ),
        Song(
            "All Quiet On The Western",
            getRaw(GlobalVariable.img.MANU17),
            getRaw(GlobalVariable.music.ALLQUIETONONTHEWESTERN),
            getRaw(GlobalVariable.gif.FUNNY),
            200
        ),
        Song(
            "Dark Is The Night",
            getRaw(GlobalVariable.img.MANU18),
            getRaw(GlobalVariable.music.DARKISTHENIGHT),
            getRaw(GlobalVariable.gif.HAPPY4),
            100
        ),
        Song(
            "I don't Want To Set The World",
            getRaw(GlobalVariable.img.MANU19),
            getRaw(GlobalVariable.music.IDONTWANTTOSET),
            getRaw(GlobalVariable.gif.DUDUBUBU1),
            250
        ),
        Song(
            "Ti Fa Stare Bene",
            getRaw(GlobalVariable.img.MANU20),
            getRaw(GlobalVariable.music.TIFASTAREBENE),
            getRaw(GlobalVariable.gif.HAPPY7),
            70
        ),
        Song(
            "ANXIETY",
            getRaw(GlobalVariable.img.MANU21),
            getRaw(GlobalVariable.music.ANXIETY),
            getRaw(GlobalVariable.gif.SFOTTI),
            50
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
    var position by mutableFloatStateOf(0f)
    var gifPosition by mutableFloatStateOf(0f)
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
    if(index == 0)
    {
        restartMusic()
        //Commento
        return;
    }

    //Modifica la canzone corrente
    index--;
    soundHandler.selectedSong = soundHandler.songList[index]
    oldSong = soundHandler.selectedSong
    oldMediaPlayer?.stop()
    oldMediaPlayer?.release()
    soundHandler.mediaPlayer = MediaPlayer.create(soundHandler.context, soundHandler.selectedSong.music)
    oldMediaPlayer = soundHandler.mediaPlayer
    state = GlobalVariable.icon.ICON_STOP
    soundHandler.position = (-150f - soundHandler.selectedSong.distance) + 0f
    soundHandler.sliderPosition = 0f
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
    soundHandler.sliderPosition = 0f
    soundHandler.position = (-150f - soundHandler.selectedSong.distance) + 0f

    soundHandler.mediaPlayer.start()

}

 @RequiresApi(Build.VERSION_CODES.P)
 fun createAnimatedImageDrawableFromImageDecoder(context: Context, uri: Uri): AnimatedImageDrawable {
    val source = ImageDecoder.createSource(context.contentResolver, uri)
    val drawable = ImageDecoder.decodeDrawable(source)
    return drawable as AnimatedImageDrawable
}


