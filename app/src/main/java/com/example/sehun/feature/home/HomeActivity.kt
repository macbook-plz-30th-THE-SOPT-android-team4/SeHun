package com.example.sehun.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.sehun.R
import com.example.sehun.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHomeViewpager2.adapter = viewPagerAdapter
    }

    private fun initBottomNavi() {
        with(binding) {
            vpHomeViewpager2.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        bnvHome.menu.getItem(position).isChecked = true
                    }
                })

            bnvHome.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_profile -> {
                        vpHomeViewpager2.currentItem = FIRST_FRAGMENT
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_profile -> {
                        vpHomeViewpager2.currentItem = SECOND_FRAGMENT
                        return@setOnItemSelectedListener true
                    }
                    else -> {
                        vpHomeViewpager2.currentItem = THIRD_FRAGMENT
                        return@setOnItemSelectedListener true
                    }
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}
