package com.example.sehun.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sehun.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
    }

    private fun initAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHomeViewpager2.adapter = viewPagerAdapter
    }
}
