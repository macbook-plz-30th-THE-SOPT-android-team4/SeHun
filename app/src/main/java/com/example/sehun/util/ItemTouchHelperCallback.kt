package com.example.sehun.feature.home

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.data.local.HomeFragmentData

class itemMoveListener(
    private val recyclerViewAdapter: FollowerAdapter,
    private val updateData: () -> (Unit),
    private val removeData: (HomeFragmentData) -> (Unit)
) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ItemTouchHelper.LEFT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        recyclerViewAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val removedFollower: HomeFragmentData =
            recyclerViewAdapter.getItemList()[viewHolder.adapterPosition]
        recyclerViewAdapter.removeItem(viewHolder.adapterPosition)
        removeData(removedFollower)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        updateData()
    }
}
