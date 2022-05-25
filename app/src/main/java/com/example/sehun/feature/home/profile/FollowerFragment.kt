package com.example.sehun.feature.home.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.R
import com.example.sehun.data.local.FollowerFragmentData
import com.example.sehun.data.remote.GitClient
import com.example.sehun.databinding.FragmentFollowerBinding
import com.example.sehun.enqueueUtil
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
        getInfo()
        initMainAdapter()
        addItemList()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getInfo() {
        followerAdapter = FollowerAdapter {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.apply {
                putExtra("name", it.name)
                putExtra("introduce", it.introduce)
            }
            startActivity(intent)
        }
        initNetwork()
    }

    private fun initMainAdapter() {
        binding.rvFollowerList.adapter = followerAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemList() {
        followerAdapter.itemList.addAll(
            listOf<FollowerFragmentData>(
                FollowerFragmentData("권용민", "1111", R.drawable.yongmin),
                FollowerFragmentData("김세훈", "2222", R.drawable.sehun),
                FollowerFragmentData("이종찬", "3333", R.drawable.jongchan),
                FollowerFragmentData("이혜빈", "4444", R.drawable.hyebin),
                FollowerFragmentData("최정원", "5555", R.drawable.jungwon)
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    private fun initNetwork() {
        val username: String = "s9hn"
        Log.d("aaaaaaaaaaaaaaaaa", "aaaaaaaaaaa")

        val call = GitClient.soptService.getGit(username)

        call.enqueueUtil(onSuccess = {
            Log.d("dqwe", "123123123")
        })
    }
}
