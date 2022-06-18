package com.example.vesdecode.CategoryAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.R
import com.example.vesdecode.databinding.ItemCategoryBinding
import com.example.vesdecode.models.Category

class CategoryRecyclerViewAdapter(items: List<Category>) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    private val items = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind((items[position]))
        if (position == selectedItemPos)
            holder.selectedBg()
        else
            holder.defaultBg()
    }

    override fun getItemCount() = items.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun selectedBg() {
            binding.btnCategory.setBackgroundColor(Color.rgb(241, 84, 18))
            binding.btnCategory.setTextColor(Color.WHITE)
        }

        fun defaultBg() {
            binding.btnCategory.setBackgroundColor(Color.WHITE)
            binding.btnCategory.setTextColor(Color.BLACK)
        }

        init {
            itemView.setOnClickListener {
                selectedItemPos = absoluteAdapterPosition
                if (lastItemSelectedPos == -1)
                    lastItemSelectedPos = selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

        fun bind(category: Category) {
            binding.btnCategory.text = category.name
        }

    }

}