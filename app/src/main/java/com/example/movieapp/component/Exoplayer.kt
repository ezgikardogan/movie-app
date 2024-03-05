package com.example.movieapp.component

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.movieapp.util.Constants
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun Exoplayer(videoKey: String) {
    val context = LocalContext.current
    val exoplayer = ExoPlayer.Builder(context).build()
    val mediaSource = remember(Constants.YOUTUBE_URL + videoKey) {
        MediaItem.fromUri(Uri.parse(Constants.YOUTUBE_URL + videoKey))
    }
    LaunchedEffect(mediaSource) {
        exoplayer.setMediaItem(mediaSource)
        exoplayer.prepare()
    }
    DisposableEffect(Unit) {
        onDispose {
            exoplayer.release()
        }
    }
    AndroidView(
        factory = { ctx ->
            StyledPlayerView(ctx).apply {
                player = exoplayer
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )

}