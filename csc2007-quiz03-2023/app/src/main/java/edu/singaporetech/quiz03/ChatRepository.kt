package edu.singaporetech.quiz03

import kotlinx.coroutines.flow.Flow

class ChatRepository(private val dao: ChatDao) {
    val chats: Flow<List<Chat>> = dao.getAll()

    suspend fun insert(chat: Chat): Long {
        return dao.insert(chat)
    }

    suspend fun update(chat_id: Long, chat_msg: String) {
        dao.update(chat_id, chat_msg)
    }
}