package edu.singaporetech.madata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FourDigitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FourDigitRepository
    val allFourDigits: LiveData<List<FourDigit>>
    val latestFourDigits: LiveData<List<FourDigit>>

    init {
        val fourDigitDao = FourDigitRoomDatabase.getDatabase(application).fourDigitDao()
        repository = FourDigitRepository(fourDigitDao)
        allFourDigits = repository.all()
        latestFourDigits = repository.latest()
    }

    fun add(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(number)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}