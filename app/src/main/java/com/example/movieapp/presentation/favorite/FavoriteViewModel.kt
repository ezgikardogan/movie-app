package com.example.movieapp.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.local.FavoriteRepository
import com.example.movieapp.data.model.Favorite
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = mutableStateOf<FavoriteUiState>(FavoriteUiState())
    val state: State<FavoriteUiState> = _state

    suspend fun getAllSQLite(): List<Favorite> {
        _state.value = FavoriteUiState(movies = favoriteRepository.getAllFavoriteMovie())
        return favoriteRepository.getAllFavoriteMovie()
    }

    suspend fun deleteInSQLite(movieId: Int) {
        return favoriteRepository.deleteFavoriteMovie(movieId)
    }

}