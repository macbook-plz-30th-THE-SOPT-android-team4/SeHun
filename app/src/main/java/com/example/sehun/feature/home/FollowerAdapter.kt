package com.example.sehun.feature.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.ItemFollowerListBinding
import com.example.sehun.feature.DetailActivity

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    val itemList = mutableListOf<HomeFragmentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeFragmentData) {
            binding.follower = data
            itemView.setOnClickListener() {
                val name = binding.tvFollowerName.text.toString()

                val followerToDetailIntent =
                    Intent(itemView.context, DetailActivity::class.java)
                followerToDetailIntent.putExtra("name", name)
                ContextCompat.startActivity(itemView.context, followerToDetailIntent, null)
            }
        }
    }
}
