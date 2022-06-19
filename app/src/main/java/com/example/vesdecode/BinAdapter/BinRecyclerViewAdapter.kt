package com.example.vesdecode.ParameterAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.R
import com.example.vesdecode.databinding.ItemBinBinding
import com.example.vesdecode.models.Bin
import com.example.vesdecode.models.Parameter

class BinRecyclerViewAdapter(items: List<Bin>) : RecyclerView.Adapter<BinRecyclerViewAdapter.BinViewHolder>() {

    private val items = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bin, parent, false)
        return BinViewHolder(
            ItemBinBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class BinViewHolder(private val binding: ItemBinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bin: Bin) {
            binding.tvName.text = bin.name
            binding.tvCounter.text = bin.counter.toString()
            binding.tvPriceCurrent.text = bin.price_current.toString() + " ₽"
            binding.tvPriceOld.text = bin.price_old.toString() + " ₽"

        }

    }
}