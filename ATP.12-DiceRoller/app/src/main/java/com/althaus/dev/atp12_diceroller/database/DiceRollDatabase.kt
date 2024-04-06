package com.althaus.dev.atp12_diceroller.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.althaus.dev.atp12_diceroller.model.DiceRoll

@Database(entities = [DiceRoll::class], version = 1, exportSchema = false)
abstract class DiceRollDatabase : RoomDatabase() {

    abstract fun diceRollDao(): DiceRollDao

    companion object {
        @Volatile
        private var INSTANCE: DiceRollDatabase? = null

        fun getDatabase(context: Context): DiceRollDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiceRollDatabase::class.java,
                    "dice_roll_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
