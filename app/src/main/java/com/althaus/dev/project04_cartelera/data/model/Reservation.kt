package com.althaus.dev.project04_cartelera.data.model

data class Reservation(
    val id: Int,
    val userId: Int,
    val movieId: Int,
    val dateTime: String
)
