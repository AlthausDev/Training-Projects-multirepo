package com.althaus.dev.project04_cartelera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dates")
data class Dates(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "maximum") val maximum: String,
    @ColumnInfo(name = "minimum") val minimum: String
)
