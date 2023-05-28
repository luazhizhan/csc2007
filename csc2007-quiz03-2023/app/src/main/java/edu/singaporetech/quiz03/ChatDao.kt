package edu.singaporetech.quiz03

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: Chat): Long

    @Query("SELECT * FROM chat")
    fun getAll(): Flow<List<Chat>>

    @Query("UPDATE chat SET chat_msg = :chat_msg WHERE chat_id = :chat_id")
    suspend fun update(chat_id: Long, chat_msg: String)
}