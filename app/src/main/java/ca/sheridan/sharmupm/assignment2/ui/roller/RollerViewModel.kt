package ca.sheridan.sharmupm.assignment2.ui.roller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.sheridan.sharmupm.assignment2.database.GameScore
import ca.sheridan.sharmupm.assignment2.database.GameScoreDao
import ca.sheridan.sharmupm.assignment2.database.GameScoreDatabase
import kotlinx.coroutines.launch
class RollerViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val _scoreId = MutableLiveData<Long>().apply{
        value = 0L
    }

    private val gamescoreDao: GameScoreDao =
            GameScoreDatabase.getInstance(application).gamescoreDao

    fun send(gamescore: GameScore) {
        viewModelScope.launch {
            gamescoreDao.insert(gamescore)
        }
    }
    val lastLineFromDB = gamescoreDao.getLastEntry()

    fun reset(){
        _scoreId.value = null
    }

}