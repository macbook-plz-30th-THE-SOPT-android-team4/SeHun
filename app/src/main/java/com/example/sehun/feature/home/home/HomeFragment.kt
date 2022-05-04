package com.example.sehun.feature.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")
    private lateinit var tabViewPagerAdapter: TabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(FollowFragment(), FollowingFragment())

        tabViewPagerAdapter = TabViewPagerAdapter(this)
        tabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHomefragmentViewpager2.adapter = tabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("{@string/}", "d")

        TabLayoutMediator(
            binding.tlHomefragmentFollow,
            binding.vpHomefragmentViewpager2
        ) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}
