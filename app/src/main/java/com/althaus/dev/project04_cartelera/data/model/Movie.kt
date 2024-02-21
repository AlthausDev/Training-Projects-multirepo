package com.althaus.dev.project04_cartelera.data.model

data class Movie(
    val id: Int,
    val title: String,
    val synopsis: String,
    val duration: Int,
    val genre: String,
    val director: String,
    val cast: List<String>,
    val releaseDate: String,
    val posterUrl: String
)
