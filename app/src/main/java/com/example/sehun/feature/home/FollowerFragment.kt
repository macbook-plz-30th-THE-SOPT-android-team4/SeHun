package com.example.sehun.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.FragmentFollowerBinding
import com.example.sehun.feature.DetailActivity

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialization")
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        addItemList()
        initMainAdapter()
        getInfo()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMainAdapter() {
        binding.rvFollowerList.adapter = followerAdapter
    }

    private fun addItemList() {
        followerAdapter.itemList.addAll(
            listOf<HomeFragmentData>(
                HomeFragmentData("권용민", "1111"),
                HomeFragmentData("김세훈", "2222"),
                HomeFragmentData("이종찬", "3333"),
                HomeFragmentData("이혜빈", "4444"),
                HomeFragmentData("최정원", "5555")
            )
        )
    }

    private fun getInfo() {
        followerAdapter = FollowerAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.apply {
                putExtra("name", it.name)
                putExtra("intro", it.introduce)
            }
            startActivity(intent)
        }
    }
}
