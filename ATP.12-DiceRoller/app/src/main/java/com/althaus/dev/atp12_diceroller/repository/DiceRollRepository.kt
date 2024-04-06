package com.althaus.dev.atp12_diceroller.repository

import com.althaus.dev.atp12_diceroller.database.DiceRollDao
import com.althaus.dev.atp12_diceroller.model.DiceRoll

class DiceRollRepository(private val diceRollDao: DiceRollDao) {

    suspend fun insertDiceRoll(diceRoll: DiceRoll) {
        diceRollDao.insert(diceRoll)
    }

    fun getAllDiceRolls() = diceRollDao.getAllDiceRolls()
}
