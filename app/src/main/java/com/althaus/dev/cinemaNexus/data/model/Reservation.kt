package com.althaus.dev.cinemaNexus.data.model

data class Reservation(
    val id: String? = null, // ID del documento (puede ser generado por Firestore)
    val movieTitle: String = "",
    val numberOfTickets: Int = 0,
    val totalPrice: Double = 0.0
)
