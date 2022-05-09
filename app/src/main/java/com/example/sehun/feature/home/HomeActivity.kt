package com.example.sehun.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sehun.R
import com.example.sehun.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        dataBinding()
    }

    private fun dataBinding() {
        binding.home = HomeData(
            "김세훈",
            "25",
            "ENFJ",
            "우하하하".repeat(30),
            R.drawable.ic_launcher_foreground
        )
    }
}