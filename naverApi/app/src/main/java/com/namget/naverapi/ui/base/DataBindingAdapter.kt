package com.namget.naverapi.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.namget.naverapi.util.setImageWithGlide

@BindingAdapter("android:url")
fun ImageView.setGlideImage(url: String) {
    setImageWithGlide(url)
}