package com.example.movieapp.presentation.favorite

import com.example.movieapp.data.model.Favorite

data class FavoriteUiState(
    val isLoading: Boolean = false,
    var movies: List<Favorite> = emptyList<Favorite>(),
    val error: String = "",
)