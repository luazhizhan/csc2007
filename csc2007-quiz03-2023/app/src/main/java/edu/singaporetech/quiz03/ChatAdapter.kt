package edu.singaporetech.quiz03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.quiz03.databinding.LeftMsgItemBinding
import edu.singaporetech.quiz03.databinding.RightMsgItemBinding

class ChatAdapter(private val listener: OnItemClickListener, var items: List<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val LEFT_MSG_TYPE = 0
    private val RIGHT_MSG_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == LEFT_MSG_TYPE) {
            val binding = LeftMsgItemBinding.inflate(inflater, parent, false)
            LeftMessageViewHolder(binding)
        } else {
            val binding = RightMsgItemBinding.inflate(inflater, parent, false)
            RightMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is LeftMessageViewHolder) {
            holder.msg.text = item.chat_msg
        } else if (holder is RightMessageViewHolder) {
            holder.msg.text = item.chat_msg
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].msg_type == LEFT_MSG_TYPE) {
            LEFT_MSG_TYPE
        } else {
            RIGHT_MSG_TYPE
        }
    }

    inner class LeftMessageViewHolder(binding: LeftMsgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val msg = binding.message

        init {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }

    inner class RightMessageViewHolder(binding: RightMsgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val msg = binding.message

        init {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }

}
