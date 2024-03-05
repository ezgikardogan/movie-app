package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.FavoriteDatabase
import com.example.movieapp.data.local.FavoriteRepository
import com.example.movieapp.data.local.FavoriteRepositoryImpl
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): FavoriteDatabase {
        return Room.databaseBuilder(
            app, FavoriteDatabase::class.java, "favoritedatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(mydb: FavoriteDatabase): FavoriteRepository {
        return FavoriteRepositoryImpl(mydb.favoriteDao())
    }

}