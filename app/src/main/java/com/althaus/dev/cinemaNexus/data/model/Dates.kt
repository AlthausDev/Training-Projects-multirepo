package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Representa un rango de fechas.
 */
@IgnoreExtraProperties
data class Dates(
    @DocumentId val id: String? = null,
    val maximum: String = "",
    val minimum: String = ""
) {
    /**
     * Convierte este modelo de rango de fechas a un mapa compatible con Firestore.
     */
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "maximum" to maximum,
            "minimum" to minimum
        )
    }

    companion object {
        /**
         * Crea una instancia de `Dates` a partir de un mapa.
         */
        fun fromMap(map: Map<String, Any?>): Dates {
            return Dates(
                id = map["id"] as? String,
                maximum = map["maximum"] as? String ?: "",
                minimum = map["minimum"] as? String ?: ""
            )
        }
    }
}
