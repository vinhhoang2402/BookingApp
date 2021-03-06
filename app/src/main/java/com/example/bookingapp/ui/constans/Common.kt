package com.example.bookingapp.ui.constans


object Common {
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"

    fun getPosterPath(posterPath: String): String {
        return BASE_POSTER_PATH + posterPath
    }
}