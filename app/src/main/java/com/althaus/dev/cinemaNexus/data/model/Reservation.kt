package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Representa una reserva de película.
 */
@IgnoreExtraProperties
data class Reservation(
    @DocumentId val id: String? = null,
    val movieTitle: String = "",
    val numberOfTickets: Int = 0,
    val totalPrice: Double = 0.0
) {
    /**
     * Convierte este modelo de reserva a un mapa compatible con Firestore.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "movieTitle" to movieTitle,
            "numberOfTickets" to numberOfTickets,
            "totalPrice" to totalPrice
        )
    }

    companion object {
        /**
         * Crea una instancia de `Reservation` a partir de un mapa.
         */
        fun fromMap(map: Map<String, Any?>): Reservation {
            return Reservation(
                id = map["id"] as? String,
                movieTitle = map["movieTitle"] as? String ?: "",
                numberOfTickets = map["numberOfTickets"] as? Int ?: 0,
                totalPrice = map["totalPrice"] as? Double ?: 0.0
            )
        }
    }
}
