package edu.singaporetech.quiz03

import androidx.lifecycle.*
import edu.singaporetech.quiz03.FunctionMap.fib
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {
    val chats: LiveData<List<Chat>> = repository.chats.asLiveData()

    fun insert(chat: Chat) {
        viewModelScope.launch {
            repository.insert(chat)

            // check for fun(fib, <number>)
            val regex = """fun\((\w+),(\d+)\)""".toRegex()
            val matchResult = regex.find(chat.chat_msg)
            if (matchResult != null && matchResult.groupValues.size == 3) {
                val functionName = matchResult.groupValues[1]
                val functionInput = matchResult.groupValues[2].toInt()

                // immediately reply to the user
                val initialResponse = "${chat.chat_msg} is ..."
                val chatResponse = Chat(chat_msg = initialResponse, msg_type = 0)
                val id = repository.insert(chatResponse)

                // compute the result in the background
                viewModelScope.launch {
                    val function = FunctionMap.map[functionName]
                    val resultDeferred = async(Dispatchers.Default) {
                        function?.invoke(functionInput) ?: "Invalid function"
                    }
                    val result = resultDeferred.await()
                    val updatedResponse = "${chat.chat_msg} is $result"
                    repository.update(id, updatedResponse)
                }
            } else {
                repository.insert(Chat(chat_msg = chat.chat_msg, msg_type = 0))
            }
        }
    }

}

class ChatViewModelFactory(private val repository: ChatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}