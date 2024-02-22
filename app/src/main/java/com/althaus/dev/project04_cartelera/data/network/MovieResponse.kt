package com.althaus.dev.project04_cartelera.data.network

import com.althaus.dev.project04_cartelera.data.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MovieResponse(private val movieApiService: MovieApiService) {

    /**
     * Recupera la lista de películas desde la API.
     *
     * @return La lista de películas obtenida de la API.
     * @throws IOException Si ocurre un error de red.
     * @throws HttpException Si la respuesta de la API es un error HTTP.
     */
    @Throws(IOException::class, HttpException::class)
    suspend fun fetchMovies(): List<Movie> {
        try {
            // Realiza la solicitud a la API utilizando Retrofit
            val response = movieApiService.getMovies()

            // Verifica si la solicitud fue exitosa
            if (response.isSuccessful) {
                // Obtiene la lista de películas de la respuesta
                val movies = response.body()

                // Devuelve la lista de películas, o una lista vacía si es nula
                return movies ?: emptyList()
            } else {
                // Lanza una excepción si hay un error en la API (error HTTP)
                throw HttpException(response)
            }
        } catch (e: IOException) {
            // Lanza una excepción si hay un error de red
            throw IOException("Error de red al obtener la lista de películas", e)
        }
    }
}
