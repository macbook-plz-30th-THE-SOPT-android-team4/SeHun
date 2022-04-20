package com.example.sehun.feature.home

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object HomeBindingAdapter {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setImageResId(imageview: ImageView, resId: Int) {
        imageview.setImageResource(resId)
    }
}
