package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movies
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.Videos

interface MovieRepository {

    suspend fun getDiscover(): Movies

    suspend fun getDetail(movieId: Int): MovieDetail

    suspend fun getVideos(movieId: Int): Videos

}