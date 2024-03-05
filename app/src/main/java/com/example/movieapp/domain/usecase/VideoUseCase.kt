package com.example.movieapp.domain.usecase

import com.example.movieapp.domain.model.Videos
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class VideoUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeMovieVideos(id: Int): Flow<Resource<Videos>> = flow {
        try {
            emit(Resource.Loading())
            val videoList = repository.getVideos(id)
            if (videoList != null) {
                emit(Resource.Success(videoList))
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