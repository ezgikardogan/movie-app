package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("title") var title: String,
    @SerializedName("id") var id: Int,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("overview") var overview: String,
    var favoriteState: Boolean = false
)