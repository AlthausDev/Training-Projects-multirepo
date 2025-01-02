package com.althaus.dev.cinemaNexus.data.network.responses

import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.data.network.services.MovieApiService
import retrofit2.HttpException
import java.io.IOException

class MovieApiResponse(private val movieApiService: MovieApiService) {

    suspend fun fetchNowPlayingMovies(): List<Movie> {
        return try {
            val response = movieApiService.getNowPlayingMovies()
            if (response.isSuccessful) {
                response.body()?.movies ?: emptyList()
            } else {
                throw HttpException(response)
            }
        } catch (e: IOException) {
            throw IOException("Network error while fetching movies", e)
        } catch (e: HttpException) {
            throw HttpException(e.response() ?: throw e)
        }
    }
}
