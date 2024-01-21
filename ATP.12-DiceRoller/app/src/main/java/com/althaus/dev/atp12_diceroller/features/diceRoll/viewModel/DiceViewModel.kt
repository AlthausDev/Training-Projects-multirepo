package com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaus.dev.atp12_diceroller.model.Dice

class DiceViewModel : ViewModel() {

    private var dado = Dice(6)

    private var _resultadoDado1 = MutableLiveData<Int>()
    private var _resultadoDado2 = MutableLiveData<Int>()

    val resultadoDado1: LiveData<Int> = _resultadoDado1
    val resultadoDado2: LiveData<Int> = _resultadoDado2

    fun rollDice(){
        _resultadoDado1.value = dado.rollDice()
        _resultadoDado2.value = dado.rollDice()
    }
}