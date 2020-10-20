package idrok.visola_khudoynazarova.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel(){

    private val player1 = MutableLiveData<Int>()
    private val player2 = MutableLiveData<Int>()
    private var listString = arrayListOf<ArrayList<String>>()

    fun getPlayer1(): LiveData<Int>{
        return player1
    }

    fun getPlayer2(): LiveData<Int>{
        return player2
    }

    fun getListText(): ArrayList<ArrayList<String>>{

        return listString

    }


    fun setPlayer1(count : Int){
        player1.value = count
    }

    fun setPlayer2(count : Int){
        player2.value = count
    }

    fun setListText(list: ArrayList<ArrayList<String>>){
        listString = list
    }

}