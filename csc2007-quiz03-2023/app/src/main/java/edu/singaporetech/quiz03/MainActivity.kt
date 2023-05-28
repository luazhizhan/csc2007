package edu.singaporetech.quiz03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ActivityScenario.launch
import edu.singaporetech.quiz03.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(
            ChatRepository(
                ChatDatabase.getInstance(application).dao(),
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up recycler view
        var items = listOf<Chat>()
        val adapter = ChatAdapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val chat = items[position]
                binding.chatInput.setText(chat.chat_msg)
            }
        }, items)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        // observe changes in the database
        viewModel.chats.observe(this) {
            adapter.items = it
            items = it
            adapter.notifyDataSetChanged()
            binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        // send button on click listener
        binding.chatSend.setOnClickListener {
            val text = binding.chatInput.text.toString()

            // check if text is empty or just contain spaces
            if (text.trim().isEmpty()) {
                return@setOnClickListener
            }

            val chat = Chat(chat_msg = text, msg_type = 1)
            viewModel.insert(chat)
            binding.chatInput.text.clear()
        }
    }
}