package com.example.sehun.feature.home.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sehun.R
import com.example.sehun.data.local.FollowerFragmentData
import com.example.sehun.databinding.ItemFollowerListBinding

class FollowerAdapter(private val itemClick: (FollowerFragmentData) -> Unit) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val itemList = mutableListOf<FollowerFragmentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding,
        private val itemClick: (FollowerFragmentData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: FollowerFragmentData) {

            Glide.with(itemView.context)
                .load(data.img)
                .circleCrop()
                .into(binding.ivFollowerProfile)

            binding.follower = data
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
