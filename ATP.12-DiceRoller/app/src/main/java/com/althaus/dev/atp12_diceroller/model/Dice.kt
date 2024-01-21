package com.althaus.dev.atp12_diceroller.model

import java.lang.IllegalArgumentException
import kotlin.random.Random

class Dice(private var numSides: Int?) {

    init {
        if (numSides == null || numSides!! <= 1) {
            numSides = 6
        }
    }

    fun rollDice(): Int {
        return Random.nextInt(1, numSides!! + 1)
    }
}