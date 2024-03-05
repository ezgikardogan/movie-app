package com.example.movieapp.presentation.detail

import com.example.movieapp.data.model.Favorite
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.VideoModel

data class DetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = "",
    val video: VideoModel? = null,
    val favoriteMovieState: List<Favorite> = emptyList<Favorite>(),
    var isFavoriteMovie: Boolean = false
)