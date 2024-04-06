package com.althaus.dev.atp12_diceroller.model

import kotlin.random.Random

data class Dice(var numSides: Int) {

    init {
        if (numSides <= 1) {
            numSides = 6
        }
    }

    fun rollDice(): Int {
        return Random.nextInt(1, numSides + 1)
    }
}