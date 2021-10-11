package com.example.bookingapp.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adrena.commerce.paging3.data.model.Movies
import com.example.bookingapp.network.Api
import java.util.*

class GetMoviesFlowPagingSource(
    private val service: Api,
    private val apiKey: String,
    private val mapper: MoviesMapper,
    private val locale: Locale
) : PagingSource<Int, Movies.Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies.Movie> {
        val position = params.key ?: 1

        Log.d("aaaaaaaa", "position : $position")

        return try {
            service.popularMovieRx(
                apiKey = apiKey,
                language = locale.language,
                page = position
            ).run {
                val data = mapper.transform(this, locale)
                Log.d("aaaaaaaa", "position : $position")
                LoadResult.Page(
                    data = data.movies,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == this.total) null else position + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movies.Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}