package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
        private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

        /**
         * Crea una instancia de `Movie` a partir de un mapa.
         *
         * @param map Mapa que contiene las propiedades de la película.
         * @return Una instancia de `Movie` basada en los valores del mapa.
         */
        fun fromMap(map: Map<String, Any?>): Movie {
            val id = map["id"] as? String ?: ""
            val title = map["title"] as? String ?: ""
            val overview = map["overview"] as? String ?: ""
            val posterPath = map["posterPath"] as? String
            val releaseDate = map["releaseDate"] as? String ?: ""
            val popularity = map["popularity"] as? Double ?: 0.0
            val voteAverage = map["voteAverage"] as? Double ?: 0.0
            val voteCount = map["voteCount"] as? Int ?: 0

            require(isValidDate(releaseDate)) {
                "Fecha de lanzamiento inválida: $releaseDate. Debe estar en formato ISO-8601 (yyyy-MM-dd)."
            }

            require(popularity >= 0) { "La popularidad no puede ser negativa: $popularity" }
            require(voteAverage in 0.0..10.0) { "La calificación debe estar entre 0 y 10: $voteAverage" }
            require(voteCount >= 0) { "El número de votos no puede ser negativo: $voteCount" }

            return Movie(
                id,
                title,
                overview,
                posterPath,
                releaseDate,
                popularity,
                voteAverage,
                voteCount
            )
        }

        /**
         * Verifica si una fecha es válida en formato ISO-8601.
         */
        private fun isValidDate(date: String): Boolean {
            return try {
                LocalDate.parse(date, dateFormatter)
                true
            } catch (e: DateTimeParseException) {
                false
            }
        }
    }

    /**
     * Determina si la película es popular según un umbral de popularidad.
     *
     * @param threshold Umbral para considerar la película popular.
     * @return `true` si la popularidad es mayor o igual al umbral.
     */
    fun isPopular(threshold: Double = 50.0): Boolean {
        return popularity >= threshold
    }

    /**
     * Formatea la fecha de lanzamiento en un formato legible.
     *
     * @param pattern Patrón de formato deseado (por defecto `dd MMM yyyy`).
     * @return Fecha formateada o mensaje de error si la fecha es inválida.
     */
    fun formattedReleaseDate(pattern: String = "dd MMM yyyy"): String {
        return try {
            val date = LocalDate.parse(releaseDate, dateFormatter)
            date.format(DateTimeFormatter.ofPattern(pattern))
        } catch (e: DateTimeParseException) {
            "Fecha inválida"
        }
    }
}
