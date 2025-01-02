package com.althaus.dev.cinemaNexus.data.network.services

import com.althaus.dev.cinemaNexus.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>
}
