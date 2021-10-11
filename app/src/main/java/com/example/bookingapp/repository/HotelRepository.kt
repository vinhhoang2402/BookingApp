package com.example.bookingapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adrena.commerce.paging3.data.model.Movies
import com.example.bookingapp.datasource.GetMoviesFlowPagingSource
import kotlinx.coroutines.flow.Flow

class HotelRepository(private val pagingSource: GetMoviesFlowPagingSource) {

    fun getHotel(): Flow<PagingData<Movies.Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}