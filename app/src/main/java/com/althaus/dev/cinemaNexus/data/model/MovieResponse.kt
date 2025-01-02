package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Representa la respuesta de una lista de películas.
 */
@IgnoreExtraProperties
data class MovieResponse(
    val movies: List<Movie> = emptyList()
) {
    /**
     * Convierte este modelo de respuesta a un mapa compatible con Firestore.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "movies" to movies.map { it.toMap() } // Convierte cada película a su representación de mapa.
        )
    }

    companion object {
        /**
         * Crea una instancia de `MovieResponse` a partir de un mapa.
         */
        fun fromMap(map: Map<String, Any?>): MovieResponse {
            val moviesList = (map["movies"] as? List<Map<String, Any?>>)?.map { Movie.fromMap(it) }
                ?: emptyList()
            return MovieResponse(
                movies = moviesList
            )
        }
    }
}
