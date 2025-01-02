package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Representa una película en Cinema Nexus.
 *
 * Incluye información básica como título, descripción, popularidad, y calificación.
 */
@IgnoreExtraProperties
data class Movie(
    @DocumentId val id: String = "",
    val title: String = "",
    val overview: String = "",
    val posterPath: String? = null,
    val releaseDate: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) {
    /**
     * Convierte este modelo de película a un mapa compatible con Firestore.
     *
     * @return Un mapa con las propiedades de la película.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "overview" to overview,
            "posterPath" to posterPath,
            "releaseDate" to releaseDate,
            "popularity" to popularity,
            "voteAverage" to voteAverage,
            "voteCount" to voteCount
        )
    }

    companion object {
        /**
         * Crea una instancia de `Movie` a partir de un mapa.
         *
         * @param map Mapa que contiene las propiedades de la película.
         * @return Una instancia de `Movie` basada en los valores del mapa.
         */
        fun fromMap(map: Map<String, Any?>): Movie {
            return Movie(
                id = map["id"] as? String ?: "",
                title = map["title"] as? String ?: "",
                overview = map["overview"] as? String ?: "",
                posterPath = map["posterPath"] as? String,
                releaseDate = map["releaseDate"] as? String ?: "",
                popularity = map["popularity"] as? Double ?: 0.0,
                voteAverage = map["voteAverage"] as? Double ?: 0.0,
                voteCount = map["voteCount"] as? Int ?: 0
            )
        }
    }
}
