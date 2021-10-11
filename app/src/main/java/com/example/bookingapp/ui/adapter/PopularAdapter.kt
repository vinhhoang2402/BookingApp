package com.example.bookingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adrena.commerce.paging3.data.model.Movies
import com.example.bookingapp.R
import com.example.bookingapp.databinding.PopularItemBinding
import com.example.bookingapp.entity.Result
import com.example.bookingapp.ui.base.adapter.BaseAdapter
import com.example.bookingapp.ui.constans.Common
import com.example.bookingapp.ui.model.KnownFor
import com.example.bookingapp.utils.loadImage
import kotlinx.android.synthetic.main.popular_item.view.*

class PopularAdapter : PagingDataAdapter<Movies.Movie, PopularAdapter.PopularViewHolder>(DiffCallback()){

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val data = getItem(position)

        data?.let { holder.bind(it) }
    }

    inner class PopularViewHolder(private val binding : PopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Movies.Movie) {
            binding.title.text = data.originalTitle
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Movies.Movie>() {
        override fun areItemsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies.Movie, newItem: Movies.Movie): Boolean {
            return oldItem == newItem
        }
    }
}