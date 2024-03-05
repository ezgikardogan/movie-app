package com.example.movieapp.data.local

import com.example.movieapp.data.model.Favorite

interface FavoriteRepository {
    suspend fun addMovie(newMovie: Favorite)

    suspend fun getAllFavoriteMovie(): List<Favorite>

    suspend fun deleteFavoriteMovie(movieId: Int)

}