package com.duonghb.testbitrise.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url == null) {
        imageView.setImageDrawable(null)
    } else {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}
