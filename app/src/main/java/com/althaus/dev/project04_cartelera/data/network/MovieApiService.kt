package com.althaus.dev.project04_cartelera.data.network

import com.althaus.dev.project04_cartelera.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies")
    suspend fun getMovies(): Response<List<Movie>>
}