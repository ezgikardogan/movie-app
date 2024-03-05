package com.example.movieapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.detail.DetailScreen
import com.example.movieapp.presentation.discover.DiscoverScreen
import com.example.movieapp.presentation.favorite.FavoriteScreen
import com.example.movieapp.util.Constants.MOVIE_ID

@Composable
fun Screen(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            DiscoverScreen(navController = navController)
        }
        composable(NavigationItem.History.route) {
            FavoriteScreen(navController = navController)
        }
        composable(route = NavigationItem.Detail.route + "/{${MOVIE_ID}}",
            arguments = listOf(
                navArgument(MOVIE_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            //* Extracting the id from the route *//*
            val uId = remember {
                it.arguments?.getInt(MOVIE_ID)
            }
            //* We check if is null *//*
            uId?.let {
                DetailScreen(navController = navController)
            }
        }
    }
}