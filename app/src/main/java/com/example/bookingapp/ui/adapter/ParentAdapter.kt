package com.example.bookingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.databinding.ParentItemBinding
import com.example.bookingapp.ui.model.MoviesResponse

class ParentAdapter: RecyclerView.Adapter<ParentAdapter.ViewHolder>(){

    private var list: ArrayList<MoviesResponse> = arrayListOf()

    inner class ViewHolder(private val binding: ParentItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesResponse) {
            val childAdapter = ChildAdapter(data.results)
            binding.rcParent.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
            binding.rcParent.adapter = childAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentAdapter.ViewHolder {
        return ViewHolder(ParentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ParentAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addAll(list: MoviesResponse) {
        this.list.add(list)
        notifyDataSetChanged()
    }
}