package com.example.movieapp.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.movieapp.component.Exoplayer
import com.example.movieapp.component.FavoriteButton
import com.example.movieapp.data.model.Favorite
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val state = detailViewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (state.video != null) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Exoplayer(
                    videoKey = state.video.key
                )

                FavoriteButton(isChecked = state.isFavoriteMovie, onChange = { check ->
                    if (check) {
                        coroutineScope.launch {
                            detailViewModel.insertInSQLite(
                                Favorite(
                                    state.movie?.id,
                                    state.movie?.title,
                                    state.movie?.posterPath,
                                    state.movie?.overview
                                )
                            )
                            detailViewModel.getMovieDetail(state.movie?.id!!)
                        }
                    } else {
                        coroutineScope.launch {
                            detailViewModel.deleteInSQLite(state.movie?.id!!)
                        }
                        detailViewModel.getMovieDetail(state.movie?.id!!)
                    }
                })
                if (state.movie != null) {
                    Text(
                        text = state.movie.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp),
                        color = Color.Black
                    )

                    Text(
                        text = state.movie.overview,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp),
                        color = Color.Black
                    )
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

