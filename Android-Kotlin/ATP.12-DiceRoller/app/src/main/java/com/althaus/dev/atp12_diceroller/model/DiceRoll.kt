package com.althaus.dev.atp12_diceroller.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(tableName = "dice_roll_table")
data class DiceRoll(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val timestamp: String = formattedTimestamp(System.currentTimeMillis()),
    val dado1: Int,
    val dado2: Int
) {
    companion object {
        private fun formattedTimestamp(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            return dateFormat.format(timestamp)
        }
    }
}
