package com.althaus.dev.cinemaNexus.data.model

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val posterPath: String? = null,
    val releaseDate: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)
