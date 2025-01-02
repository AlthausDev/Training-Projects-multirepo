package com.althaus.dev.cinemaNexus.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_responses")
data class MovieResponse(
    @SerializedName("results")
    val movies: List<Movie>
)