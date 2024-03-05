package com.example.movieapp.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, val icon: ImageVector?, var title: String) {
    object Home : NavigationItem("Home", Icons.Rounded.Home, "Home")
    object History : NavigationItem("Favorite", Icons.Rounded.Favorite, "Favorite")
    object Detail : NavigationItem("Detail", Icons.Rounded.Favorite, "Detail")
}