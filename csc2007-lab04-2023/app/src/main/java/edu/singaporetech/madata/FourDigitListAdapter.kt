package edu.singaporetech.madata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.madata.databinding.ItemDigitBinding

class FourDigitListAdapter(
    var items: List<FourDigit>,
) : RecyclerView.Adapter<FourDigitListAdapter.FourDigitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourDigitViewHolder {
        return FourDigitViewHolder(
            ItemDigitBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FourDigitViewHolder, position: Int) {
        val item = items[position]
        holder.textViewId.text = item.id.toString()
        holder.textViewValue.text = item.value
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FourDigitViewHolder(binding: ItemDigitBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewId = binding.textViewId
        val textViewValue = binding.textViewValue
    }
}