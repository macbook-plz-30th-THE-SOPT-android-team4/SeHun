package com.example.sehun.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.sehun.dpToPixel

class ItemDecoration(
    private val myOffset: Int,
    private val myRound: Int,
    private val myColor: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = myOffset.dpToPixel()
        outRect.set(offset, offset, offset, offset)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val strokeWidth = 3
        val paint = Paint().apply {
            this.color = myColor
            this.style = Paint.Style.STROKE
            this.strokeWidth = strokeWidth.dpToPixel().toFloat()
        }

        parent.children.forEach { child ->
            c.drawRoundRect(
                (child.left).toFloat(),
                (child.top).toFloat(),
                (child.right).toFloat(),
                (child.bottom).toFloat(),
                myRound.dpToPixel().toFloat(),
                myRound.dpToPixel().toFloat(),
                paint
            )
        }
    }
}
