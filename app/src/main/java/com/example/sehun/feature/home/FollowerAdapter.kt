package com.example.sehun.feature.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.ItemFollowerListBinding
import com.example.sehun.feature.DetailActivity

class FollowerAdapter() :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val itemList = mutableListOf<HomeFragmentData>()

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeFragmentData) {
            binding.follower = data
            itemView.setOnClickListener {
                val name = binding.tvFollowerName.text.toString()

                val followerToDetailIntent =
                    Intent(itemView.context, DetailActivity::class.java)
                followerToDetailIntent.putExtra("name", name)
                ContextCompat.startActivity(itemView.context, followerToDetailIntent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowerViewHolder(binding) // itemClick
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun moveItem(fromPosition: Int, toPosition: Int) {
//        itemList[fromPosition].Order = itemList[toPosition].followerOrder.also {
//            if (fromPosition < toPosition) {
//                for (i in toPosition downTo fromPosition + 1) {
//                    itemList[i].followerOrder = itemList[i - 1].followerOrder
//                }
//                itemList.add(toPosition, itemList.removeAt(fromPosition))
//            } else if (toPosition < fromPosition) {
//                for (i in toPosition until fromPosition) {
//                    itemList[i].followerOrder = itemList[i + 1].followerOrder
//                }
//                itemList.add(toPosition, itemList.removeAt(fromPosition))
//            }
//        }
//        Timber.i(itemList.toString())
//        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    @JvmName("getItemList1")
    fun getItemList(): List<HomeFragmentData> = itemList
}
