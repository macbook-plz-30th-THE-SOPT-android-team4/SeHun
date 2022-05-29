package com.example.sehun.feature.home.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.data.remote.GitClient
import com.example.sehun.data.remote.response.ResponseHome
import com.example.sehun.databinding.FragmentFollowerBinding
import com.example.sehun.enqueueUtil

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
        initNetwork()
        getInfo()
        initMainAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getInfo() {
        followerAdapter = FollowerAdapter {
            callWeb(it.html_url)
        }
    }

    private fun initMainAdapter() {
        binding.rvFollowerList.adapter = followerAdapter
    }

    private fun initNetwork() {
        val username = "s9hn"
        val call = GitClient.soptService.getGit(username)

        call.enqueueUtil(onSuccess = {
            addItemList(it as MutableList<ResponseHome>)
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addItemList(data: List<ResponseHome>) {
        followerAdapter.itemList = data as MutableList<ResponseHome>
        followerAdapter.notifyDataSetChanged()
    }

    private fun callWeb(url: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }
}
