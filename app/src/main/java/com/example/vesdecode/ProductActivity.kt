package com.example.vesdecode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vesdecode.ParameterAdapter.ParameterRecyclerViewAdapter
import com.example.vesdecode.ParameterAdapter.ParameterRepository
import com.example.vesdecode.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private val repository = ParameterRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityProductBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent

        var arrParameters = repository.getItems()

        binding.tvName.text = intent.getStringExtra("name")
        binding.tvDescription.text = intent.getStringExtra("description")
        binding.btnBin.text = intent.getStringExtra("price_current")
        arrParameters[0].value = intent.getStringExtra("measure")
        arrParameters[1].value = intent.getStringExtra("energy_per_100_grams")
        arrParameters[2].value = intent.getStringExtra("proteins_per_100_grams")
        arrParameters[3].value = intent.getStringExtra("fats_per_100_grams")
        arrParameters[4].value = intent.getStringExtra("carbohydrates_per_100_grams")

        var adapterParameter = ParameterRecyclerViewAdapter(arrParameters)

        with(binding.rwParameters) {
            adapter = adapterParameter
            layoutManager = LinearLayoutManager(context)
        }
        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.btnBin.setOnClickListener {
            val intent = Intent(this, BinActivity::class.java)
            startActivity(intent)
        }

    }
}