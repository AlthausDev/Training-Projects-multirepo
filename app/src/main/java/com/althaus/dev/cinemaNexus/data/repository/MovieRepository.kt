package com.althaus.dev.cinemaNexus.data.repository

import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.data.network.services.MovieApiService
import javax.inject.Inject

/**
 * Repositorio para interactuar con las API relacionadas con películas.
 */
class MovieRepository @Inject constructor(
    private val movieApiService: MovieApiService
) {

    /**
     * Obtiene la lista de películas en cartelera (Now Playing).
     *
     * @return Lista de películas en cartelera.
     * @throws Exception Si ocurre un error al obtener los datos.
     */
    suspend fun getNowPlayingMovies(): List<Movie> {
        val response = movieApiService.getNowPlayingMovies()
        if (response.isSuccessful) {
            val movies = response.body()?.movies ?: emptyList()
            println("Películas obtenidas: $movies") // Depuración
            return movies
        } else {
            throw Exception("Error al obtener películas: ${response.message()} (Código: ${response.code()})")
        }
    }


    /**
     * Obtiene los detalles de una película por su ID.
     *
     * @param movieId ID de la película a buscar.
     * @return Objeto [Movie] con los detalles de la película.
     * @throws Exception Si la película no se encuentra o hay un error en la solicitud.
     */
    suspend fun getMovieById(movieId: Int): Movie {
        val response = movieApiService.getMovieDetails(movieId)
        if (response.isSuccessful) {
            return response.body()
                ?: throw Exception("Detalles de la película no encontrados para el ID: $movieId")
        } else {
            throw Exception("Error al obtener detalles de la película: ${response.message()} (Código: ${response.code()})")
        }
    }

    /**
     * Busca películas por término en la API.
     *
     * @param query Cadena de búsqueda.
     * @return Lista de películas que coincidan con el término.
     * @throws Exception Si ocurre un error durante la solicitud.
     */
    suspend fun searchMovies(query: String): List<Movie> {
        val response = movieApiService.searchMovies(query)
        if (response.isSuccessful) {
            return response.body()?.movies ?: emptyList()
        } else {
            throw Exception("Error al buscar películas: ${response.message()} (Código: ${response.code()})")
        }
    }
}
