package com.example.banking

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.banking.databinding.ActivityFirstPageBinding

class First_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnter.setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
        }
    }
}