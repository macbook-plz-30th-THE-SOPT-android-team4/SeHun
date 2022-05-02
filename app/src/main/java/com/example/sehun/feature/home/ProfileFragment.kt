package com.example.sehun.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sehun.R
import com.example.sehun.data.local.HomeData
import com.example.sehun.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initTransactionEvent()
        dataBinding()
        return binding.root
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.fcv_home_swaplist, followerFragment)
            .commit()

        with(binding) {
            btnHomeFollowerlist.setOnClickListener {
                childFragmentManager.beginTransaction().replace(
                    R.id.fcv_home_swaplist,
                    followerFragment
                ).commit()
            }

            btnHomeRepositorylist.setOnClickListener {
                childFragmentManager.beginTransaction().replace(
                    R.id.fcv_home_swaplist,
                    repositoryFragment
                ).commit()
            }
        }
    }

    private fun dataBinding() {
        binding.home = HomeData(
            "김세훈",
            "25",
            "ENFJ",
            "우하하\n".repeat(5),
            R.drawable.ic_launcher_foreground
        )
    }
}
