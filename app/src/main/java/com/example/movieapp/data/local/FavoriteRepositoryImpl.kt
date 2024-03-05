package com.example.movieapp.data.local

import com.example.movieapp.data.model.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
) : FavoriteRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override suspend fun addMovie(newMovie: Favorite) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.insert(newMovie)
        }
    }

    override suspend fun getAllFavoriteMovie(): List<Favorite> {
        return withContext(Dispatchers.IO) {
            favoriteDao.getAllFavorite()
        }
    }

    override suspend fun deleteFavoriteMovie(movieId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            favoriteDao.delete(movieId)
        }
    }

}