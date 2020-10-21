package ca.sheridan.sharmupm.assignment2.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.sheridan.sharmupm.assignment2.database.GameScore
import ca.sheridan.sharmupm.assignment2.database.GameScoreDao
import ca.sheridan.sharmupm.assignment2.database.GameScoreDatabase
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application){
    private val gamescoreDao: GameScoreDao =
            GameScoreDatabase.getInstance(application).gamescoreDao

    val history: LiveData<List<GameScore>> = gamescoreDao.getAll()
    //val mailbox: LiveData<List<GameScore>> = gamescoreDao.get(envelopeKey)

    fun clear(){
        viewModelScope.launch {
            gamescoreDao.deleteAll()
        }
    }
}