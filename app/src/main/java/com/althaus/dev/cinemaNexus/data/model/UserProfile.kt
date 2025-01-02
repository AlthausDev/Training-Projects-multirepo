package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

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
    val favorites: List<String> = emptyList(), // Lista de películas favoritas (IDs o títulos)
    val reservationHistory: List<String> = emptyList(), // Historial de reservas (IDs de reservas)
    val bio: String = "", // Breve descripción del usuario
    val creationDate: Timestamp = Timestamp.now(),
    val isVerified: Boolean = false // Verificación del correo
) {
    /**
     * Convierte este perfil de usuario a un mapa compatible con Firestore.
     *
     * @return Un mapa con las propiedades del perfil del usuario.
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
        /**
         * Crea una instancia de `UserProfile` a partir de un mapa.
         *
         * @param map Mapa que contiene las propiedades del perfil de usuario.
         * @return Una instancia de `UserProfile` basada en los valores del mapa.
         */
        fun fromMap(map: Map<String, Any?>): UserProfile {
            return UserProfile(
                id = map["id"] as? String ?: "",
                name = map["name"] as? String ?: "",
                email = map["email"] as? String ?: "",
                profileImage = map["profileImage"] as? String,
                favorites = map["favorites"] as? List<String> ?: emptyList(),
                reservationHistory = map["reservationHistory"] as? List<String> ?: emptyList(),
                bio = map["bio"] as? String ?: "",
                creationDate = map["creationDate"] as? Timestamp ?: Timestamp.now(),
                isVerified = map["isVerified"] as? Boolean ?: false
            )
        }
    }
}
