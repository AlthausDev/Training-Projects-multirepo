package com.althaus.dev.cinemaNexus.data.network.services

import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz para interactuar con la API de películas utilizando Retrofit.
 */
interface MovieApiService {

    /**
     * Obtiene la lista de películas en cartelera (Now Playing).
     *
     * @param language Idioma para los resultados (por defecto "es-ES").
     * @param page Número de página para la paginación (por defecto 1).
     * @return [Response] con un objeto [MovieResponse] que contiene las películas.
     */
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "1cf7f34f8170b757084490da8bb3a04b"
    ): Response<MovieResponse>

    /**
     * Obtiene los detalles de una película por su ID.
     *
     * @param movieId ID de la película.
     * @param language Idioma para los resultados (por defecto "es-ES").
     * @return [Response] con un objeto [Movie] que contiene los detalles.
     */
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "es-ES"
    ): Response<Movie>

    /**
     * Busca películas por término en la API.
     *
     * @param query Término de búsqueda.
     * @param language Idioma para los resultados (por defecto "es-ES").
     * @param page Número de página para la paginación (por defecto 1).
     * @return [Response] con un objeto [MovieResponse] que contiene las películas.
     */
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    /**
     * Obtiene la lista de películas populares.
     *
     * @param language Idioma para los resultados (por defecto "es-ES").
     * @param page Número de página para la paginación (por defecto 1).
     * @return [Response] con un objeto [MovieResponse] que contiene las películas populares.
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    /**
     * Obtiene la lista de películas por género.
     *
     * @param genreId ID del género.
     * @param language Idioma para los resultados (por defecto "es-ES").
     * @param page Número de página para la paginación (por defecto 1).
     * @return [Response] con un objeto [MovieResponse] que contiene las películas del género.
     */
    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>
}
