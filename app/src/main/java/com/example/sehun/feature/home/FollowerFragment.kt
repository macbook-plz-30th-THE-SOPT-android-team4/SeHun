package com.example.sehun.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sehun.R
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.FragmentFollowerBinding
import com.example.sehun.util.ItemDecoration

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialization")
    private val followerAdapter = FollowerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        addItem()
        initMainAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMainAdapter() {
        binding.rvFollowerList.adapter = followerAdapter
    }

    private fun itemDecoration() {
        binding.rvFollowerList.addItemDecoration(
            ItemDecoration(
                3,
                5,
                ContextCompat.getColor(requireContext(), R.color.purple_500)
            )
        )
    }

    private fun addItem() {
        followerAdapter.itemList.addAll(
            listOf<HomeFragmentData>(
                HomeFragmentData("권용민", "1"),
                HomeFragmentData("김세훈", "2"),
                HomeFragmentData("이종찬", "3"),
                HomeFragmentData("이혜빈", "4"),
                HomeFragmentData("최정원", "5")
            )
        )
    }
}
