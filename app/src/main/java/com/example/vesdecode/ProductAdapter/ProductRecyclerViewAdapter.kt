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
import com.example.vesdecode.models.Category
import com.example.vesdecode.models.Product


class ProductRecyclerViewAdapter(items: List<Product>) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    private val items = items.toMutableList()

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
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ProductViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            if (product.price_old != null) {
                val price = product.price_current.toString() + " ₽ " + product.price_old.toString() + " ₽"
                val spannableString = SpannableString(price)
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
                spannableString.setSpan(greySpan, product.price_current.toString().length + 3, price.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(normalSpan, product.price_current.toString().length + 3, price.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                binding.btnPrice.text = spannableString
            }
            else binding.btnPrice.text = product.price_current.toString() + " ₽"

            binding.tvName.text = product.name
            binding.tvMeasure.text = product.measure.toString() + " " + product.measure_unit
        }

    }
}