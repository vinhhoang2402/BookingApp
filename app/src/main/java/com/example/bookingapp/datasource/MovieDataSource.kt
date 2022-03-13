package com.example.bookingapp.datasource

import com.example.bookingapp.network.Api
import com.example.bookingapp.ui.model.MoviesResponse
import java.util.*

class MovieDataSource(
    private val api: Api,
    private val apiKey: String,
    private val locale: Locale
    ) {

    suspend fun getPopularDataSource(): MoviesResponse {
        return api.popularRx(
            apiKey = apiKey,
            language = locale.language)
    }

    suspend fun getRateDataSource(): MoviesResponse {
        return api.rateMovieRx(
            apiKey = apiKey,
            language = locale.language)
    }

    suspend fun getTrendDataSource(): MoviesResponse {
        return api.upcomingMovieRx(
            apiKey = apiKey,
            language = locale.language)
    }

    suspend fun getLatestDataSource(): MoviesResponse {
        return api.latestMovieRx(
            apiKey = apiKey,
            language = locale.language)
    }
}