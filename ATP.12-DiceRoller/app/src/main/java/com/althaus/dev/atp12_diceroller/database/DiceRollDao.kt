package com.althaus.dev.atp12_diceroller.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.althaus.dev.atp12_diceroller.model.DiceRoll

@Dao
interface DiceRollDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diceRoll: DiceRoll)

    @Query("SELECT * FROM dice_roll_table ORDER BY id DESC")
    fun getAllDiceRolls(): LiveData<List<DiceRoll>>
}
