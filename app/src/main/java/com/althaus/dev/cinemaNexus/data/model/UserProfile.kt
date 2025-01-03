package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.regex.Pattern

/**
 * Representa el perfil de un usuario en Cinema Nexus.
 *
 * Incluye detalles relevantes como nombre, correo, imagen de perfil,
 * historial de reservas y lista de favoritos.
 */
@IgnoreExtraProperties
data class UserProfile(
    @DocumentId val id: String = "",
    val name: String = "",
    val email: String = "",
    val profileImage: String? = null,
    val favorites: List<String> = emptyList(),
    val reservationHistory: List<String> = emptyList(),
    val bio: String = "",
    val creationDate: Timestamp = Timestamp.now(),
    val isVerified: Boolean = false
) {
    init {
        require(isValidEmail(email)) { "El correo electrónico no es válido: $email" }
        require(favorites.all { it.isNotEmpty() }) { "La lista de favoritos contiene valores nulos o vacíos." }
        require(reservationHistory.all { it.isNotEmpty() }) { "El historial de reservas contiene valores nulos o vacíos." }
    }

    /**
     * Convierte este perfil de usuario a un mapa compatible con Firestore.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "email" to email,
            "profileImage" to profileImage,
            "favorites" to favorites,
            "reservationHistory" to reservationHistory,
            "bio" to bio,
            "creationDate" to creationDate,
            "isVerified" to isVerified
        )
    }

    companion object {
        private val emailPattern = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )

        /**
         * Verifica si un correo tiene un formato válido.
         */
        private fun isValidEmail(email: String): Boolean {
            return emailPattern.matcher(email).matches()
        }

        /**
         * Crea una instancia de `UserProfile` a partir de un mapa.
         *
         * @param map Mapa que contiene las propiedades del perfil de usuario.
         * @return Una instancia de `UserProfile`.
         * @throws IllegalArgumentException si los datos son inválidos.
         */
        fun fromMap(map: Map<String, Any?>): UserProfile {
            val id = map["id"] as? String ?: ""
            val name = map["name"] as? String ?: ""
            val email = map["email"] as? String ?: ""
            val profileImage = map["profileImage"] as? String
            val favorites = map["favorites"] as? List<String> ?: emptyList()
            val reservationHistory = map["reservationHistory"] as? List<String> ?: emptyList()
            val bio = map["bio"] as? String ?: ""
            val creationDate = map["creationDate"] as? Timestamp ?: Timestamp.now()
            val isVerified = map["isVerified"] as? Boolean ?: false

            return UserProfile(
                id,
                name,
                email,
                profileImage,
                favorites,
                reservationHistory,
                bio,
                creationDate,
                isVerified
            )
        }
    }

    /**
     * Calcula la antigüedad del perfil en días.
     */
    fun profileAgeInDays(): Long {
        val creationDateTime =
            creationDate.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        val now = LocalDateTime.now()
        return ChronoUnit.DAYS.between(creationDateTime, now)
    }

    /**
     * Agrega una película a la lista de favoritos si no está ya presente.
     */
    fun addFavorite(movieId: String): UserProfile {
        if (movieId.isNotEmpty() && !favorites.contains(movieId)) {
            return copy(favorites = favorites + movieId)
        }
        return this
    }

    /**
     * Elimina una película de la lista de favoritos.
     */
    fun removeFavorite(movieId: String): UserProfile {
        if (favorites.contains(movieId)) {
            return copy(favorites = favorites - movieId)
        }
        return this
    }

    /**
     * Verifica si una película está en la lista de favoritos.
     */
    fun isFavorite(movieId: String): Boolean {
        return favorites.contains(movieId)
    }
}
