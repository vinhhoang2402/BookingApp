package com.example.bookingapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.R
import kotlinx.android.synthetic.main.onboarding_viewpager_item.view.*

class OnBoardingPagerAdapter : RecyclerView.Adapter<OnBoardingPagerAdapter.ViewHolder>() {
    private val imagesList = arrayListOf(
            R.drawable.anhdep, R.drawable.anhdep, R.drawable.anhdep,
            R.drawable.anhdep, R.drawable.anhdep
    )
    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_viewpager_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.img_on_boarding.setImageResource(imagesList[position])
    }

    override fun getItemCount(): Int = imagesList.size
}