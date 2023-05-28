package edu.singaporetech.sitiwsp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.sitiwsp.databinding.JobItemBinding

class LeftListAdapter(
    private val listener: OnItemClickListener,
    var items: List<Job>,
) : RecyclerView.Adapter<LeftListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            JobItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.company.text = item.company
        holder.salary.text = item.salary.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }

        val company = binding.company
        val salary = binding.salary
    }
}