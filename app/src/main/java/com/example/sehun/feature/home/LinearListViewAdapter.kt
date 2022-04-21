package com.example.sehun.feature.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.R
import java.util.*

class LinearListViewAdapter(private val list: MutableList<String>) :
    RecyclerView.Adapter<LinearListViewAdapter.ViewHolder>(),
    ItemTouchHelperCallback.OnItemMoveListener {

    private lateinit var dragListener: OnStartDragListener

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_linear, parent, false)
    ) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivMenu: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            with(holder) {
                tvTitle.text = it
                ivMenu.setOnTouchListener { view, event ->
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        dragListener.onStartDrag(this)
                    }
                    return@setOnTouchListener false
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}
