package com.example.bookingapp.common.Resource

import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.datasource.GetMoviesFlowPagingSource
import com.example.bookingapp.datasource.MovieDataSource
import com.example.bookingapp.datasource.MoviesMapper
import com.example.bookingapp.network.Api
import com.example.bookingapp.network.RetrofitNetwork
import com.example.bookingapp.repository.HotelRepository
import com.example.bookingapp.repository.MovieRepository
import com.example.bookingapp.repository.MovieRepositoryImp
import com.example.bookingapp.ui.factory.ViewModelFactory
import java.util.*

object Injector {

    private fun provideLocale(): Locale = Locale.getDefault()


    fun GetFlowPagingSource(): ViewModelProvider.Factory {
        val pagingSource =
            GetMoviesFlowPagingSource(
                service = RetrofitNetwork().buildApi(Api::class.java),
                apiKey = "21440930b1350cd8b4d28accce3a4799",
                mapper = MoviesMapper(),
                locale = provideLocale()
            )

        val movieRepository = MovieRepositoryImp(
            movieDataSource = getMovieDataSource()
        )
        val repository =
            HotelRepository(
                pagingSource = pagingSource
            )

        return ViewModelFactory(
            repository, movieRepository
        )
    }

    fun getMovieDataSource(): MovieDataSource {
        return MovieDataSource(
            api = RetrofitNetwork().buildApi(Api::class.java),
            apiKey = "21440930b1350cd8b4d28accce3a4799",
            locale = provideLocale()
        )
    }
}