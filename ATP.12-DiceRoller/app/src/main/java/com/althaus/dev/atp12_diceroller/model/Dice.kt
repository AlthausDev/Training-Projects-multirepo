package com.althaus.dev.atp12_diceroller.model

import java.lang.IllegalArgumentException
import kotlin.random.Random

class Dice(private var numSides: Int) {

    init {
        try {
            require(numSides > 1)
        } catch (e: IllegalArgumentException){
            numSides = 6
        }
    }

    fun rollDice(): Int {
        return Random.nextInt(1, numSides +1)
    }
}