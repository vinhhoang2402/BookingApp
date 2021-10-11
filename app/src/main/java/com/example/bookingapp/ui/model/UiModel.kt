package com.example.bookingapp.ui.model

import com.adrena.commerce.paging3.data.model.Movies

sealed class UiModel {
    data class MovieItem(val movie: Movies.Movie) : UiModel()
    data class SeparatorItem(val description: String): UiModel()
}