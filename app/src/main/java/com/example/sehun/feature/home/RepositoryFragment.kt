package com.example.sehun.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.FragmentRepositoryBinding
import com.example.sehun.feature.DetailActivity

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialization")
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        getInfo()
        initMainAdapter()
        addItem()
        return binding.root
    }

    private fun getInfo() {
        repositoryAdapter = RepositoryAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.apply {
                putExtra("name", it.name)
                putExtra("introduce", it.introduce)
            }
            startActivity(intent)
        }
    }

    private fun initMainAdapter() {
        binding.rvRepositoryList.adapter = repositoryAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addItem() {
        repositoryAdapter.itemList.addAll(
            listOf<HomeFragmentData>(
                HomeFragmentData("권용민 과제\n레포지토리", "파트과제1"),
                HomeFragmentData("김세훈 과제\n레포지토리", "파트과제2"),
                HomeFragmentData("이종찬 과제\n레포지토리", "파트과제3"),
                HomeFragmentData("이혜빈 과제\n레포지토리", "파트과제4"),
                HomeFragmentData("최정원 과제\n레포지토리", "파트과제5")
            )
        )
    }
}
