package com.example.sehun.feature.home.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.remote.response.ResponseHome
import com.example.sehun.databinding.ItemFollowerListBinding

class FollowerAdapter(private val itemClick: (ResponseHome) -> Unit) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    var itemList = mutableListOf<ResponseHome>()

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
        private val itemClick: (ResponseHome) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ResponseHome) {

            binding.follower = data
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
