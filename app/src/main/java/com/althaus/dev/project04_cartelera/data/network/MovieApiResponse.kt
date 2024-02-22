package com.althaus.dev.project04_cartelera.data.network

import com.althaus.dev.project04_cartelera.data.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MovieApiResponse(private val movieApiService: MovieApiService) {

    @Throws(IOException::class, HttpException::class)
    suspend fun fetchNowPlayingMovies(): List<Movie> {
        try {
            val response = movieApiService.getNowPlayingMovies()
            if (response.isSuccessful) {
                return response.body()?.results ?: emptyList()
            } else {
                throw HttpException(response)
            }
        } catch (e: IOException) {
            throw IOException("Error de red al obtener la lista de películas", e)
        }
    }
}
