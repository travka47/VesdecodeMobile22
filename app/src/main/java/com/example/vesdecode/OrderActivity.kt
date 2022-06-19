package com.example.vesdecode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vesdecode.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityOrderBinding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_order)

    }
}