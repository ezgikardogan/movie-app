package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.Movies
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    //Use case -> only one major public function, one feature, single responsibility
    fun executeGetDiscover(): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getDiscover()
            if (movieList != null) {
                emit(Resource.Success(movieList))
            } else {
                emit(Resource.Error(message = "No movie found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }


}