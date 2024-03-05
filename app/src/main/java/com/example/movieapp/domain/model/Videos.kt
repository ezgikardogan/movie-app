package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("id")
    var id: Int,
    @SerializedName("results")
    var results: ArrayList<VideoModel>
)


