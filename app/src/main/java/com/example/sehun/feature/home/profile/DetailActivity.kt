package com.example.sehun.feature.home.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInfo()
    }

    private fun setInfo() {
        val name = intent.getStringExtra("name")
        val intro = intent.getStringExtra("introduce")
        binding.tvDetailName.text = name.toString()
        binding.tvDetailDetailintro.text = intro.toString()
    }
}
