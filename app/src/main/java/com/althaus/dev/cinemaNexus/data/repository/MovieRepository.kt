package com.althaus.dev.cinemaNexus.repository

import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.data.network.services.MovieApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getNowPlayingMovies(): List<Movie> {
        val response = movieApiService.getNowPlayingMovies()
        if (response.isSuccessful) {
            return response.body()?.movies ?: emptyList()
        } else {
            throw Exception("Error al obtener películas: ${response.message()}")
        }
    }

    suspend fun getMovieById(movieId: Int): Movie {
        // Ejemplo de endpoint para detalles (ajustar según API real)
        val response = movieApiService.getMovieDetails(movieId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Detalles no encontrados")
        } else {
            throw Exception("Error al obtener detalles: ${response.message()}")
        }
    }
}
