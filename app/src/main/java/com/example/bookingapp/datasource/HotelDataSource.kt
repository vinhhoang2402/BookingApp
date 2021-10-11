package com.example.bookingapp.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bookingapp.common.Constants.NETWORK_PAGE_SIZE
import com.example.bookingapp.common.Constants.STARTING_PAGE_INDEX
import com.example.bookingapp.entity.Result
import com.example.bookingapp.network.Api
import com.example.bookingapp.ui.constans.LogUtil
import retrofit2.HttpException
import java.io.IOException

class HotelDataSource(private val api: Api) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: STARTING_PAGE_INDEX
        Log.d("HotelDataSource",  "$page")
        return try {
            //val api = api as Api
            Log.d( "HotelDataSource","$page")

            val response = api.getHotel(pageNumber = page, pageSize = NETWORK_PAGE_SIZE)
            val hotels = response.data.body.searchResults.results
            Log.d( "HotelDataSource", hotels.size.toString())
            LoadResult.Page(
                data = hotels,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.data.body.searchResults.totalCount) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}