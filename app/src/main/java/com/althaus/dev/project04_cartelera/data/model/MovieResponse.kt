package com.althaus.dev.project04_cartelera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_responses")
data class MovieResponse(
    @SerializedName("results")
    val movies: List<Movie>
)