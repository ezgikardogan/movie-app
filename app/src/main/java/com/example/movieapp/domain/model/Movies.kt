package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    var page: Int = 1,
    @SerializedName("results")
    var movieList: ArrayList<MovieModel>?,
    @SerializedName("total_pages")
    var totalPage: Int = 0,
    @SerializedName("total_results")
    var totalResults: Int = 0
)