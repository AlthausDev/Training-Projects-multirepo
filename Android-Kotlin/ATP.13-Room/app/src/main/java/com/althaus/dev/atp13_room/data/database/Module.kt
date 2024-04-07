package com.althaus.dev.atp13_room.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "module")
data class Module(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val credits: Int
)