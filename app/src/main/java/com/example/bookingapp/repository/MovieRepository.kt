package com.example.bookingapp.repository

import com.example.bookingapp.datasource.MovieDataSource
import com.example.bookingapp.ui.model.MoviesResponse

interface MovieRepository {
    suspend fun getPopularRepo(): MoviesResponse
    suspend fun getRateRepo(): MoviesResponse
    suspend fun getTrendRepo(): MoviesResponse
    suspend fun getLatestRepo(): MoviesResponse
}