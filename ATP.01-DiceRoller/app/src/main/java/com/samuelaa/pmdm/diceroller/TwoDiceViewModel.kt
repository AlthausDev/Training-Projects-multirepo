package com.samuelaa.pmdm.diceroller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoDiceViewModel(numSides: Int): ViewModel() {

    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    private val _caraDado1 = MutableLiveData(dice1.diceRoll())
    val caraDado1: LiveData<Int> = _caraDado1

    private val _caraDado2 = MutableLiveData(dice2.diceRoll())
    val caraDado2: LiveData<Int> = _caraDado2

}