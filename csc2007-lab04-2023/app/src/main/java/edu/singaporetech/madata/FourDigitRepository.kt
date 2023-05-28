package edu.singaporetech.madata

import androidx.lifecycle.LiveData

class FourDigitRepository(private val fourDigitDao: FourDigitDao) {

    suspend fun add(number: Int) {
        fourDigitDao.add(FourDigit(value = number.toString()))
    }

    suspend fun deleteAll() {
        fourDigitDao.deleteAll()
    }

    fun all(): LiveData<List<FourDigit>> {
        return fourDigitDao.all()
    }

    fun latest(): LiveData<List<FourDigit>> {
        return fourDigitDao.latest()
    }

}