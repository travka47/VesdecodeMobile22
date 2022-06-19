package com.example.vesdecode.ParameterAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.R
import com.example.vesdecode.databinding.ItemParameterBinding
import com.example.vesdecode.models.Parameter

class ParameterRecyclerViewAdapter(items: List<Parameter>) : RecyclerView.Adapter<ParameterRecyclerViewAdapter.ParameterViewHolder>() {

    private val items = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parameter, parent, false)
        return ParameterViewHolder(
            ItemParameterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ParameterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ParameterViewHolder(private val binding: ItemParameterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(parameter: Parameter) {
            binding.tvName.text = parameter.name
            binding.tvValue.text = parameter.value
        }

    }
}