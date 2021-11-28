package com.example.bookingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.databinding.ChildItemBinding
import com.example.bookingapp.ui.model.MoviesResponse

class ChildAdapter(private val list: List<MoviesResponse.Movie>): RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ChildItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesResponse.Movie) {
            binding.title.text = data.originalTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildAdapter.ViewHolder {
        return ViewHolder(ChildItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ChildAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}