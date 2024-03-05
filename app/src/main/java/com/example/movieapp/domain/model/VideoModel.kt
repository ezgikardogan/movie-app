package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("key")
    var key: String,
    @SerializedName("site")
    var site: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("name")
    var name: String
)