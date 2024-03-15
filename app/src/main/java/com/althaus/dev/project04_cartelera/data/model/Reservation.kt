package com.althaus.dev.project04_cartelera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class Reservation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_title") val movieTitle: String,
    @ColumnInfo(name = "number_of_tickets") val numberOfTickets: Int
)
