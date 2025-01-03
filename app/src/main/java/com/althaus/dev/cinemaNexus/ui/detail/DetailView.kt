package com.althaus.dev.cinemaNexus.ui.detail

import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.data.model.Movie
import com.squareup.picasso.Picasso

@Composable
fun DetailView(
    viewModel: DetailViewModel,
    movieId: Int,
    onBack: () -> Unit
) {
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    val movieDetails = viewModel.movieDetails.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    when {
        isLoading.value -> LoadingView()
        errorMessage.value != null -> ErrorView(
            message = errorMessage.value!!,
            onRetry = { viewModel.fetchMovieDetails(movieId) })

        movieDetails.value != null -> MovieDetailContent(
            movie = movieDetails.value!!,
            onBack = onBack
        )
    }
}

@Composable
fun MovieDetailContent(movie: Movie, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Póster de la película con Picasso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                },
                update = { imageView ->
                    Picasso.get()
                        .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                        .placeholder(R.drawable.logo) // Imagen de marcador de posición
                        .error(R.drawable.ic_launcher_background) // Imagen de error
                        .into(imageView)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha de lanzamiento
        Text(
            text = "Fecha de lanzamiento: ${movie.releaseDate}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descripción
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de regreso
        Button(onClick = onBack) {
            Text(text = "Volver")
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Error: $message", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(text = "Reintentar")
            }
        }
    }
}
