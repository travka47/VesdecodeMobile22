package com.example.vesdecode.ProductAdapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.R
import com.example.vesdecode.databinding.ItemCardBinding


class ProductRecyclerViewAdapter : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ProductViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (position % 3 == 0) holder.bind()
    }

    override fun getItemCount(): Int {
        return 9
    }

    inner class ProductViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val spannableString = SpannableString("720₽ 800₽")
            val normalSpan = StyleSpan(Typeface.NORMAL)
            val greySpan = object : ClickableSpan() {
                override fun onClick(widget: View) = Unit
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = Color.rgb(135, 135, 139)
                    ds.isUnderlineText = false
                    ds.textSize = 38f
                    ds.isStrikeThruText = true
                }
            }
            spannableString.setSpan(greySpan, 5, 9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(normalSpan, 5, 9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            binding.btnPrice.text = spannableString
        }

    }
}