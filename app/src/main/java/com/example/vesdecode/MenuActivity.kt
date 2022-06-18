package com.example.vesdecode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.CategoryAdapter.CategoryRecyclerViewAdapter
import com.example.vesdecode.CategoryAdapter.CategoryRepository
import com.example.vesdecode.ProductAdapter.ProductRecyclerViewAdapter
import com.example.vesdecode.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var adapterCategory: CategoryRecyclerViewAdapter

    private lateinit var recyclerViewProduct: RecyclerView
    private lateinit var adapterProduct: ProductRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityMenuBinding
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val repository = CategoryRepository()
        recyclerViewCategory = findViewById(R.id.rwCategories)
        adapterCategory = CategoryRecyclerViewAdapter(repository.getItems())
        recyclerViewProduct = findViewById(R.id.rwProducts)
        adapterProduct = ProductRecyclerViewAdapter()

        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.adapter = adapterCategory
        recyclerViewProduct.adapter = adapterProduct

    }

}