package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("title")
    var title: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("poster_path") var posterPath: String
)