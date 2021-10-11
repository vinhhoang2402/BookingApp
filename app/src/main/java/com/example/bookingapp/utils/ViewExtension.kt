package com.example.bookingapp.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.bookingapp.R

fun View.enable() {
    alpha = 1.0f
    isEnabled = true
}

fun View.disable() {
    alpha = 0.3f
    isEnabled = false
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hidden() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}