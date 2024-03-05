package com.example.movieapp.presentation.discover

import com.example.movieapp.data.model.Favorite
import com.example.movieapp.domain.model.MovieModel

data class DiscoverUIState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList<MovieModel>(),
    val error: String = "",
    val favoriteMovieState: List<Favorite> = emptyList<Favorite>()
)