package com.example.bookingapp.datasource

import android.media.Image
import androidx.paging.PagingData
import androidx.paging.map
import com.adrena.commerce.paging3.data.model.Movies
import com.example.bookingapp.ui.model.MoviesResponse
import java.text.SimpleDateFormat
import java.util.*

class MoviesMapper {

    fun transform(response: MoviesResponse, locale: Locale): Movies {
        return with(response) {
            Movies(
                total = total,
                page = page,
                movies = results.map {
                    Movies.Movie(
                        0,
                        it.id,
                        it.popularity,
                        it.video,
                       null,
                        it.adult,
                        null,
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                SimpleDateFormat("YYYY-mm-dd", locale).parse(date)
                            } else {
                                null
                            }
                        }
                    )
                }
            )
        }
    }
}