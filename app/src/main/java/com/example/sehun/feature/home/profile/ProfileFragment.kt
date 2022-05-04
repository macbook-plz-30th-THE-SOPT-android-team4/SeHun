package com.example.sehun.feature.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
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
        imageGlide()

        return binding.root
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.fcv_home_swaplist, followerFragment)
            .commit()

        with(binding) {

            btnHomeFollowerlist.isSelected = true

            btnHomeFollowerlist.setOnClickListener {
                btnHomeFollowerlist.isSelected = true
                btnHomeRepositorylist.isSelected = false

                childFragmentManager.beginTransaction().replace(
                    R.id.fcv_home_swaplist,
                    followerFragment
                ).commit()
            }

            btnHomeRepositorylist.setOnClickListener {
                btnHomeRepositorylist.isSelected = true
                btnHomeFollowerlist.isSelected = false

                childFragmentManager.beginTransaction().replace(
                    R.id.fcv_home_swaplist,
                    repositoryFragment
                ).commit()
            }
        }
    }

    private fun dataBinding() {
        binding.home = HomeData(
            "SEHUN",
            "@S2ehun",
            "코린? 개린?이 입니다.",
            R.drawable.doran
        )
    }

    private fun imageGlide() {
        Glide.with(this)
            .load(R.drawable.doran)
            .circleCrop()
            .into(binding.ivHomeUserimage)
    }
}
