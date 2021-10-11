package com.example.bookingapp.repository

import com.adrena.commerce.paging3.data.model.Movies

sealed class UiModel {
    data class RepoItem(val repo: Movies.Movie) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}