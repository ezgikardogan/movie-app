package com.example.movieapp.presentation.discover

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieapp.component.FavoriteButton
import com.example.movieapp.domain.model.MovieModel
import com.example.movieapp.util.Constants

@Composable
fun DiscoverListRow(
    movie: MovieModel,
    onItemClick: (MovieModel) -> Unit,
    onFavoriteClick: (Boolean, MovieModel) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(movie) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = rememberImagePainter(data = Constants.IMAGE_URL +movie.posterPath),
            contentDescription = movie.overview,
            modifier = Modifier
                .padding(16.dp)
                .size(100.dp, 100.dp)
                .clip(RectangleShape)
        )
        FavoriteButton(isChecked = movie.favoriteState, onChange = {
            if (it) {
                onFavoriteClick(true, movie)
            } else {
                onFavoriteClick(false, movie)
            }
        })

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                movie.title,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                movie.overview,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }

}