package com.example.sehun.feature.home.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.local.RepositoryFragmentData
import com.example.sehun.databinding.ItemRepositoryListBinding

class RepositoryAdapter(private val itemClick: (RepositoryFragmentData) -> Unit) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    val itemList = mutableListOf<RepositoryFragmentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoryViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class RepositoryViewHolder(
        private val binding: ItemRepositoryListBinding,
        private val itemClick: (RepositoryFragmentData) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RepositoryFragmentData) {

            binding.repository = data
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }
}
