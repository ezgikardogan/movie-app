package com.example.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Favorite(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("uuid")
    var uuid: Int?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val movieName: String?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val movieImage: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val movieDescription: String?
)