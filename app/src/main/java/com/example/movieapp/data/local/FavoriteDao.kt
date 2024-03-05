package com.example.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.model.Favorite

@Dao
interface FavoriteDao {
    //Data Access Object

    @Insert
    suspend fun insertAll(vararg movies: Favorite): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: Favorite)

    //Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite(): List<Favorite>

    @Query("SELECT * FROM favorite ")
    suspend fun getFavoriteMovie(): Favorite

    @Query("DELETE FROM favorite WHERE uuid= :movieId")
    suspend fun delete(movieId: Int)

}