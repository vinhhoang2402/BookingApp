package com.example.bookingapp.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.network.Api
import com.example.bookingapp.network.RetrofitNetwork
import com.example.bookingapp.repository.HotelRepository
import com.example.bookingapp.ui.viewmodel.PopularViewModel

class ViewModelFactory(private val repository: HotelRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(PopularViewModel::class.java) -> PopularViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}