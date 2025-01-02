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
}
