package com.example.movieapp.data.remote

import com.example.movieapp.domain.model.Movies
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.model.Videos
import com.example.movieapp.util.Constants.AUTHORIZATION
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieAPI {

    @Headers(AUTHORIZATION)
    @GET("3/discover/movie")
    suspend fun getDiscover(): Movies

    @Headers(AUTHORIZATION)
    @GET("3/movie/{movie_id}")
    suspend fun getDetail(@Path("movie_id") movieId: Int): MovieDetail

    @Headers(AUTHORIZATION)
    @GET("3/movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") movieId: Int): Videos

}