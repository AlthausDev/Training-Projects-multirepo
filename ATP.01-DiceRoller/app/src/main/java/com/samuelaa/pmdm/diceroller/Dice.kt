package com.samuelaa.pmdm.diceroller

internal class Dice (val numSides: Int ) {

    fun diceRoll() : Int{
        return (1..numSides).random()

    }
}