package com.example.vesdecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vesdecode.models.Category
import com.example.vesdecode.CategoryAdapter.CategoryRecyclerViewAdapter
import com.example.vesdecode.ProductAdapter.ProductRecyclerViewAdapter
import com.example.vesdecode.databinding.ActivityMenuBinding
import com.example.vesdecode.models.Product
import com.example.vesdecode.models.Tag
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MenuActivity : AppCompatActivity() {

    private val arrCategories = mutableListOf<Category>()
    private val arrTags = mutableListOf<Tag>()
    private val arrProducts = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMenuBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        readJsonCategory()
        readJsonTag()
        readJsonProduct()

        var adapterCategory = CategoryRecyclerViewAdapter(arrCategories)
        var adapterProduct = ProductRecyclerViewAdapter(arrProducts)

        with(binding.rwCategories) {
            adapter = adapterCategory
            layoutManager = LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
        }

        with(binding.rwProducts) {
            adapter = adapterProduct
        }
        adapterProduct.setItemClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("name", arrProducts[it].name)
            intent.putExtra("description", arrProducts[it].description)
            intent.putExtra("measure", arrProducts[it].measure.toString() + " " + arrProducts[it].measure_unit)
            intent.putExtra("energy_per_100_grams", arrProducts[it].energy_per_100_grams.toString() + " ккал")
            intent.putExtra("proteins_per_100_grams", arrProducts[it].proteins_per_100_grams.toString() + " г")
            intent.putExtra("fats_per_100_grams", arrProducts[it].fats_per_100_grams.toString() + " г")
            intent.putExtra("carbohydrates_per_100_grams", arrProducts[it].carbohydrates_per_100_grams.toString() + " г")
            intent.putExtra("price_current", "В корзину за " + arrProducts[it].price_current + " ₽")
            startActivity(intent)
        }
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