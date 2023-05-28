package edu.singaporetech.grocerylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.grocerylist.databinding.ItemGroceryBinding

open class GroceryAdapter(
    private var list: ArrayList<GroceryModel>,
) : RecyclerView.Adapter<GroceryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemGroceryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = list[position]
        holder.groceryListTextView.text = model.title
        holder.groceryListTextView.isChecked = model.checked == true
        holder.itemView.setOnClickListener {
            if (model.checked == true) {
                model.checked = false
                holder.groceryListTextView.isChecked = false
            } else {
                model.checked = true
                holder.groceryListTextView.isChecked = true
            }
            notifyItemChanged(position)
        }
    }

    fun updateList(newList: ArrayList<GroceryModel>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(binding: ItemGroceryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val groceryListTextView = binding.groceryListTextView
    }
}