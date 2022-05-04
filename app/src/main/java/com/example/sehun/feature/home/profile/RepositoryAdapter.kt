package com.example.sehun.feature.home.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.local.HomeFragmentData
import com.example.sehun.databinding.ItemRepositoryListBinding
import com.example.sehun.feature.DetailActivity

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    val itemList = mutableListOf<HomeFragmentData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class RepositoryViewHolder(
        private val binding: ItemRepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HomeFragmentData) {
            binding.repository = data
            itemView.setOnClickListener {
                val name = binding.tvRepositoryName.text.toString()

                val repositoryToDetailIntent =
                    Intent(itemView.context, DetailActivity::class.java)
                repositoryToDetailIntent.putExtra("name", name)
                ContextCompat.startActivity(itemView.context, repositoryToDetailIntent, null)
            }
        }
    }
}
