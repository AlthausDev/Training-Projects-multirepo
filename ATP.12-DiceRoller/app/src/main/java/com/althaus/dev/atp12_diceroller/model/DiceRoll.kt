package com.althaus.dev.atp12_diceroller.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dice_roll_table")
data class DiceRoll(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timestamp: String,
    val dice1Result: Int,
    val dice2Result: Int
)
