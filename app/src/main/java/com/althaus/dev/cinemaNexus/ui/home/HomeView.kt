package com.althaus.dev.cinemaNexus.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.ui.components.MovieItem
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout

@Composable
fun HomeView(
    viewModel: HomeViewModel,
    onNavigateToDetails: (Int) -> Unit
) {
    val homeState = viewModel.homeState.collectAsState()

    BaseLayout(
        showAppBar = true,
        appBarTitle = "Cartelera de Películas",
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        when (val state = homeState.value) {
            is HomeState.Loading -> LoadingView()
            is HomeState.Success -> MovieList(
                movies = state.movies,
                onNavigateToDetails = onNavigateToDetails
            )

            is HomeState.Empty -> EmptyView(message = "No hay películas disponibles")
            is HomeState.Error -> ErrorView(message = state.message)
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
fun MovieList(
    movies: List<Movie>,
    onNavigateToDetails: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onClick = { onNavigateToDetails(movie.id as Int) }
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun EmptyView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

