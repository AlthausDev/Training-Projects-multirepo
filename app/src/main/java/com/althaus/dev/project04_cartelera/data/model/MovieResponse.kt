package com.althaus.dev.project04_cartelera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_responses")
data class MovieResponse(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "dates") val dates: Dates,
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "results") val results: List<Movie>,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int
)
