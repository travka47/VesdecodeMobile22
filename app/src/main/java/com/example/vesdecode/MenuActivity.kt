package com.example.vesdecode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vesdecode.models.Category
import com.example.vesdecode.CategoryAdapter.CategoryRecyclerViewAdapter
import com.example.vesdecode.ProductAdapter.ProductRecyclerViewAdapter
import com.example.vesdecode.databinding.ActivityMenuBinding
import com.example.vesdecode.models.Product
import com.example.vesdecode.models.Tag
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MenuActivity : AppCompatActivity() {
    val arrCategories = mutableListOf<Category>()
    val arrTags = mutableListOf<Tag>()
    val arrProducts = mutableListOf<Product>()

    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var adapterCategory: CategoryRecyclerViewAdapter

    private lateinit var recyclerViewProduct: RecyclerView
    private lateinit var adapterProduct: ProductRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityMenuBinding
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        readJsonCategory()
        readJsonTag()
        readJsonProduct()
        recyclerViewCategory = findViewById(R.id.rwCategories)
        adapterCategory = CategoryRecyclerViewAdapter(arrCategories)
        recyclerViewProduct = findViewById(R.id.rwProducts)
        adapterProduct = ProductRecyclerViewAdapter(arrProducts)

        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.adapter = adapterCategory
        recyclerViewProduct.adapter = adapterProduct
    }

    private fun readJsonCategory() {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("Categories.json")
            var json = inputStream.bufferedReader().use { it.readText() }
            var jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                var jsonobj = jsonarr.getJSONObject(i)
                var category = Category(
                    id = jsonobj.getInt("id"),
                    name = jsonobj.getString("name")
                )
                arrCategories.add(category)
            }
        }
        catch (e : IOException) {
        }
    }

    private fun readJsonTag() {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("Tags.json")
            var json = inputStream.bufferedReader().use { it.readText() }
            var jsonarr = JSONArray(json)
            for (i in 0 until jsonarr.length()) {
                var jsonobj = jsonarr.getJSONObject(i)
                var tag = Tag(
                    id = jsonobj.getInt("id"),
                    name = jsonobj.getString("name")
                )
                arrTags.add(tag)
            }
        }
        catch (e : IOException) {
        }
    }

    private fun readJsonProduct() {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("Products.json")
            var json = inputStream.bufferedReader().use { it.readText() }
            var jsonarr = JSONArray(json)
            var array = mutableListOf<Int>()
            for (i in 0 until jsonarr.length()) {
                val jsonobj = jsonarr.getJSONObject(i)
                val arr = jsonobj.getJSONArray("tag_ids")
                if (arr.length() == 0 ) {
                    for (k in 0 until arr.length()) {
                        array[k] = arr[k] as Int;
                    }
                }
                else array = mutableListOf()

                var priceOld = jsonobj.getString("price_old")
                var crutch: Int?
                if (priceOld === "null") crutch = null
                else crutch = jsonobj.getInt("price_old")

                var product = Product(
                    id = jsonobj.getInt("id"),
                    category_id = jsonobj.getInt("category_id"),
                    name = jsonobj.getString("name"),
                    description = jsonobj.getString("description"),
                    image = jsonobj.getString("image"),
                    price_current = jsonobj.getInt("price_current"),
                    price_old = crutch,
                    measure = jsonobj.getInt("measure"),
                    measure_unit = jsonobj.getString("measure_unit"),
                    energy_per_100_grams = jsonobj.getDouble("energy_per_100_grams"),
                    proteins_per_100_grams = jsonobj.getDouble("proteins_per_100_grams"),
                    fats_per_100_grams = jsonobj.getDouble("fats_per_100_grams"),
                    carbohydrates_per_100_grams = jsonobj.getDouble("carbohydrates_per_100_grams"),
                    tag_ids = array
                )
                arrProducts.add(product)
            }
        }
        catch (e : IOException) {
        }
    }

}