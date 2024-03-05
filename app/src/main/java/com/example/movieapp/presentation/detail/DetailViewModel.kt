package com.example.movieapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.local.FavoriteRepository
import com.example.movieapp.data.model.Favorite
import com.example.movieapp.domain.usecase.MovieDetailUseCase
import com.example.movieapp.domain.usecase.VideoUseCase
import com.example.movieapp.util.Constants.IMAGE_URL
import com.example.movieapp.util.Constants.MOVIE_ID
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val videoUseCase: VideoUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    init {
        savedStateHandle.get<Int>(MOVIE_ID)?.let {
            getMovieDetail(it)
        }
        viewModelScope.launch {
            getAllSQLite()
        }
    }

    fun getMovieDetail(movieId: Int) {
        movieDetailUseCase.executeGetMovieDetail(movieId).onEach {
            when (it) {
                is Resource.Success -> {
                    getVideos(it.data?.id!!)
                    _state.update { currentState ->
                        currentState.copy(movie = it.data)
                    }
                    _state.value.isFavoriteMovie = _state.value.favoriteMovieState.contains(
                        Favorite(
                            uuid = it.data.id,
                            movieName = it.data.title,
                            movieImage = IMAGE_URL + it.data.posterPath,
                            movieDescription = it.data.overview
                        )
                    )
                    _state.value = DetailUiState(isLoading = false)
                }

                is Resource.Error -> {
                    _state.value = DetailUiState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = DetailUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getVideos(id: Int) {
        videoUseCase.executeMovieVideos(id).onEach { video ->
            when (video) {
                is Resource.Success -> {
                    video.data?.results?.map { videoData ->
                        if (videoData.name == "Official Trailer") {
                            _state.update { currentState ->
                                currentState.copy(video = videoData)
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    _state.value = DetailUiState(error = video.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = DetailUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    suspend fun insertInSQLite(movie: Favorite) {
        favoriteRepository.addMovie(movie)
    }

    suspend fun getAllSQLite(): List<Favorite> {
        _state.value =
            DetailUiState(favoriteMovieState = favoriteRepository.getAllFavoriteMovie())
        return favoriteRepository.getAllFavoriteMovie()
    }

    suspend fun deleteInSQLite(movieId: Int) {
        return favoriteRepository.deleteFavoriteMovie(movieId)
    }

}
