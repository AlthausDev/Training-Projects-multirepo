package com.althaus.dev.cinemaNexus.data.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

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
        private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

        /**
         * Crea una instancia de `Dates` a partir de un mapa.
         */
        fun fromMap(map: Map<String, Any?>): Dates {
            val id = map["id"] as? String
            val maximum = map["maximum"] as? String ?: ""
            val minimum = map["minimum"] as? String ?: ""

            require(isValidDate(maximum) && isValidDate(minimum)) {
                "Fechas inválidas: maximum='$maximum', minimum='$minimum'. Deben estar en formato ISO-8601 (yyyy-MM-dd)."
            }

            require(LocalDate.parse(minimum).isBefore(LocalDate.parse(maximum))) {
                "El rango de fechas es inválido: minimum debe ser anterior a maximum."
            }

            return Dates(id = id, maximum = maximum, minimum = minimum)
        }

        /**
         * Verifica si una cadena es una fecha válida en formato ISO-8601.
         */
        private fun isValidDate(date: String): Boolean {
            return try {
                LocalDate.parse(date, formatter)
                true
            } catch (e: DateTimeParseException) {
                false
            }
        }
    }

    /**
     * Calcula la duración en días entre minimum y maximum.
     */
    fun durationInDays(): Long {
        val minDate = LocalDate.parse(minimum)
        val maxDate = LocalDate.parse(maximum)
        return ChronoUnit.DAYS.between(minDate, maxDate)
    }

    /**
     * Verifica si una fecha está dentro del rango.
     */
    fun isDateInRange(date: String): Boolean {
        require(isValidDate(date)) { "La fecha proporcionada no es válida: $date" }
        val targetDate = LocalDate.parse(date)
        val minDate = LocalDate.parse(minimum)
        val maxDate = LocalDate.parse(maximum)
        return !targetDate.isBefore(minDate) && !targetDate.isAfter(maxDate)
    }
}
