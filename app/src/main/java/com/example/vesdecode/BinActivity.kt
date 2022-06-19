package com.example.vesdecode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vesdecode.BinAdapter.BinRepository
import com.example.vesdecode.ParameterAdapter.BinRecyclerViewAdapter
import com.example.vesdecode.databinding.ActivityBinBinding

class BinActivity : AppCompatActivity() {

    private val repository = BinRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityBinBinding = ActivityBinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrBin= repository.getItems()
        var adapterBin = BinRecyclerViewAdapter(arrBin)

        with(binding.rwBin) {
            adapter = adapterBin
            layoutManager = LinearLayoutManager(context)
        }
        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.btnBin.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

    }
}