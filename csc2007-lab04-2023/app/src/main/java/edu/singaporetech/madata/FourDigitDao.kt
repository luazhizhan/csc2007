package edu.singaporetech.madata

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FourDigitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(digit: FourDigit)

    @Query("SELECT * FROM four_digit")
    fun all(): LiveData<List<FourDigit>>

    @Query("SELECT * FROM four_digit ORDER BY id DESC LIMIT 1")
    fun latest(): LiveData<List<FourDigit>>

    @Query("DELETE FROM four_digit")
    suspend fun deleteAll()
}