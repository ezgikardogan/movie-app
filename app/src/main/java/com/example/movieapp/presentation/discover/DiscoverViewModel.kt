package com.example.movieapp.presentation.discover

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavoriteRepository
import com.example.movieapp.data.model.Favorite
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.domain.usecase.MovieUseCase
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = mutableStateOf<DiscoverUIState>(DiscoverUIState())
    val state: State<DiscoverUIState> = _state

    init {
        getDiscover()
        viewModelScope.launch {
            getAllSQLite()
        }
    }

    fun getDiscover() {
        movieUseCase.executeGetDiscover().onEach {
            when (it) {
                is Resource.Success -> {
                    val list: MutableList<MovieModel> = emptyList<MovieModel>().toMutableList()
                    it.data?.movieList?.map { movie ->
                        movie.favoriteState = _state.value.favoriteMovieState.contains(
                            Favorite(
                                uuid = movie.id,
                                movieName = movie.title,
                                movieImage = "http://image.tmdb.org/t/p/w300${movie.posterPath}",
                                movieDescription = movie.overview
                            )
                        )
                        list += movie
                    }
                    _state.value = DiscoverUIState(movies = list)
                }

                is Resource.Error -> {
                    _state.value = DiscoverUIState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = DiscoverUIState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun insertInSQLite(movie: Favorite) {
        favoriteRepository.addMovie(movie)
    }

    suspend fun getAllSQLite(): List<Favorite> {
        _state.value =
            DiscoverUIState(favoriteMovieState = favoriteRepository.getAllFavoriteMovie())
        return favoriteRepository.getAllFavoriteMovie()
    }

    suspend fun deleteInSQLite(movieId: Int) {
        return favoriteRepository.deleteFavoriteMovie(movieId)
    }

}