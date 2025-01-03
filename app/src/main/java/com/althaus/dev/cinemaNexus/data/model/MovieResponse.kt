package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

/**
 * Representa la respuesta de una lista de películas.
 */
@IgnoreExtraProperties
data class MovieResponse(
    @SerializedName("results")
    val movies: List<Movie>
) {
    /**
     * Convierte este modelo de respuesta a un mapa compatible con Firestore.
     *
     * @return Un mapa con la representación de la lista de películas.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "movies" to movies.map { it.toMap() } // Convierte cada película a su representación de mapa.
        )
    }

    companion object {
        /**
         * Crea una instancia de `MovieResponse` a partir de un mapa.
         *
         * @param map Mapa que contiene las propiedades de la respuesta.
         * @return Una instancia de `MovieResponse`.
         * @throws IllegalArgumentException si los datos del mapa son inválidos.
         */
        fun fromMap(map: Map<String, Any?>): MovieResponse {
            val moviesList = (map["movies"] as? List<Map<String, Any?>>)?.mapNotNull {
                try {
                    Movie.fromMap(it)
                } catch (e: Exception) {
                    null // Ignoramos películas inválidas
                }
            } ?: emptyList()

            return MovieResponse(movies = moviesList)
        }
    }

    /**
     * Filtra las películas según una condición dada.
     *
     * @param predicate Condición para filtrar las películas.
     * @return Una lista de películas que cumplen con la condición.
     */
    fun filterMovies(predicate: (Movie) -> Boolean): List<Movie> {
        return movies.filter(predicate)
    }

    /**
     * Calcula el promedio de calificaciones (voteAverage) de las películas.
     *
     * @return El promedio de calificaciones o 0.0 si no hay películas.
     */
    fun averageRating(): Double {
        return if (movies.isNotEmpty()) {
            movies.sumOf { it.voteAverage } / movies.size
        } else {
            0.0
        }
    }

    /**
     * Obtiene las películas populares según un umbral de popularidad.
     *
     * @param threshold Umbral para determinar si una película es popular (por defecto, 50.0).
     * @return Lista de películas populares.
     */
    fun getPopularMovies(threshold: Double = 50.0): List<Movie> {
        return movies.filter { it.popularity >= threshold }
    }
}
