package com.example.bookingapp.ui.base.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bookingapp.R

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {

    Glide.with(imageView.context)
        .load(imgUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.phongnhakebang)
                .error(R.drawable.ic_launcher_background)
        )
        .into(imageView)
}