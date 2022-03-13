package com.example.bookingapp.network

import com.example.bookingapp.common.Constants.API_HOST
import com.example.bookingapp.common.Constants.API_KEY
import com.example.bookingapp.entity.Hotel
import com.example.bookingapp.ui.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("list")
    @Headers(
        "x-rapidapi-key: $API_KEY",
        "x-rapidapi-host: $API_HOST"
    )
    suspend fun getHotel(
        @Query("destinationId") destinationId: String = "1506246",
        @Query("pageNumber") pageNumber: Int = 0,
        @Query("pageSize") pageSize: Int = 0,
        @Query("checkIn") checkIn: String = "2020-01-08",
        @Query("checkOut") checkOut: String = "2020-01-15",
        @Query("adults1") adults1: Int = 1
    ): Hotel

    @GET("movie/popular")
    suspend fun MovieRx(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): MoviesResponse

    @GET("movie/popular")
    suspend fun popularRx(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MoviesResponse

    @GET("movie/top_rated")
    suspend fun rateMovieRx(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MoviesResponse

    @GET("movie/upcoming")
    suspend fun upcomingMovieRx(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MoviesResponse

    @GET("movie/now_playing")
    suspend fun latestMovieRx(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MoviesResponse
}