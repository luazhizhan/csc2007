package edu.singaporetech.quiz03

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val chat_id: Long = 0,
    val chat_msg: String,
    val msg_type: Int,
)