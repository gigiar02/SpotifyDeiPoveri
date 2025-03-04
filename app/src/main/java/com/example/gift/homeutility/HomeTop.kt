package com.example.gift.homeutility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.gift.R

//Variabili globali utilizzate nel progetto
enum class GlobalVariable
{
    HOME_IMAGE;
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

//Canzone
class Song
{
    val title : String
    val image : Int
    val music : Int

    constructor(title : String, image : Int, music : Int)
    {
        this.title = title
        this.image = image
        this.music = music
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