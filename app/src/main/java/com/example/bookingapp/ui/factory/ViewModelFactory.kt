package com.example.bookingapp.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.bookingapp.repository.HotelRepository
import com.example.bookingapp.repository.MovieRepository
import com.example.bookingapp.ui.viewmodel.MovieViewModel
import com.example.bookingapp.ui.viewmodel.PopularViewModel

class ViewModelFactory(
    private val repository: HotelRepository,
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PopularViewModel::class.java) -> PopularViewModel(repository) as T
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(
                movieRepository
            ) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}