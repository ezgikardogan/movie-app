package com.example.movieapp.data.repository

import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.domain.model.Movies
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.Videos
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI) : MovieRepository {

    override suspend fun getDiscover(): Movies {
        return api.getDiscover()
    }

    override suspend fun getDetail(movieId: Int): MovieDetail {
        return api.getDetail(movieId)
    }

    override suspend fun getVideos(movieId: Int): Videos {
        return api.getVideos(movieId)
    }

}