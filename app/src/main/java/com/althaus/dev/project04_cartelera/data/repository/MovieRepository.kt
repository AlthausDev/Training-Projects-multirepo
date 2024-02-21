package com.althaus.dev.project04_cartelera.data.repository

import com.althaus.dev.project04_cartelera.data.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getMovieById(movieId: Int): Movie?
    suspend fun saveMovies(movies: List<Movie>)
}
