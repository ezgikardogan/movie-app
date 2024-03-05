package com.example.movieapp.presentation.discover

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.data.model.Favorite
import com.example.movieapp.presentation.main.NavigationItem
import com.example.movieapp.util.Constants.IMAGE_URL
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DiscoverScreen(navController: NavController, viewModel: DiscoverViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column() {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) { movie ->
                    DiscoverListRow(movie = movie, onItemClick = {
                        //navigate to details
                        navController.navigate(NavigationItem.Detail.route + "/${movie.id}")
                    }, onFavoriteClick = { checked, it ->
                        if (checked) {
                            coroutineScope.launch {
                                viewModel.insertInSQLite(
                                    Favorite(
                                        it.id,
                                        it.title,
                                        IMAGE_URL+it.posterPath,
                                        it.overview
                                    )
                                )
                                viewModel.getDiscover()
                            }
                        } else {
                            coroutineScope.launch {
                                viewModel.deleteInSQLite(it.id)
                                viewModel.getDiscover()
                            }
                        }
                    })
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}