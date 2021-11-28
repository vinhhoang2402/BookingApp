package com.example.bookingapp.repository

import com.example.bookingapp.datasource.MovieDataSource
import com.example.bookingapp.ui.model.MoviesResponse

class MovieRepositoryImp(private val movieDataSource: MovieDataSource): MovieRepository {
    override suspend fun getPopularRepo(): MoviesResponse {
        return movieDataSource.getPopularDataSource()
    }

    override suspend fun getRateRepo(): MoviesResponse {
        return movieDataSource.getRateDataSource()
    }

    override suspend fun getTrendRepo(): MoviesResponse {
        return movieDataSource.getTrendDataSource()
    }


}