package com.example.sehun.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setImageResId(imageview: ImageView, resId: Int) {
        imageview.setImageResource(resId)
    }
}
