package com.example.bookingapp.network

import com.example.bookingapp.common.Constants.API_HOST
import com.example.bookingapp.common.Constants.API_KEY
import com.example.bookingapp.common.Resource.Resource
import com.example.bookingapp.entity.Hotel
import com.example.bookingapp.ui.model.MoviesResponse
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
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
        @Query("destinationId") destinationId : String = "1506246",
        @Query("pageNumber") pageNumber: Int = 0,
        @Query("pageSize") pageSize: Int = 0,
        @Query("checkIn") checkIn: String = "2020-01-08",
        @Query("checkOut") checkOut: String = "2020-01-15",
        @Query("adults1") adults1: Int = 1
    ): Hotel

    @GET("movie/popular")
    suspend fun popularMovieRx(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ) : MoviesResponse
}