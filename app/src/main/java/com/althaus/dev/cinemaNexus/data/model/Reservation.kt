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
    init {
        require(numberOfTickets >= 0) { "El número de entradas no puede ser negativo." }
        require(totalPrice >= 0.0) { "El precio total no puede ser negativo." }
    }

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
         *
         * @param map Mapa que contiene las propiedades de la reserva.
         * @return Una instancia de `Reservation`.
         * @throws IllegalArgumentException si los datos son inválidos.
         */
        fun fromMap(map: Map<String, Any?>): Reservation {
            val id = map["id"] as? String
            val movieTitle = map["movieTitle"] as? String ?: ""
            val numberOfTickets = (map["numberOfTickets"] as? Number)?.toInt() ?: 0
            val totalPrice = (map["totalPrice"] as? Number)?.toDouble() ?: 0.0

            require(numberOfTickets >= 0) { "El número de entradas no puede ser negativo." }
            require(totalPrice >= 0.0) { "El precio total no puede ser negativo." }

            return Reservation(id, movieTitle, numberOfTickets, totalPrice)
        }
    }

    /**
     * Calcula el precio promedio por entrada.
     *
     * @return El precio promedio por entrada o 0.0 si no hay entradas.
     */
    fun averagePricePerTicket(): Double {
        return if (numberOfTickets > 0) totalPrice / numberOfTickets else 0.0
    }

    /**
     * Verifica si la reserva es válida.
     *
     * @return `true` si la reserva tiene un número positivo de entradas y un precio total coherente.
     */
    fun isValidReservation(): Boolean {
        return numberOfTickets > 0 && totalPrice >= 0.0
    }
}
