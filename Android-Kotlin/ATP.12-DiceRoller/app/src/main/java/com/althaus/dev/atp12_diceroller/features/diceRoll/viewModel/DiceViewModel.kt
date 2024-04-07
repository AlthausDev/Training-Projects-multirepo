package com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.atp12_diceroller.model.Dice
import com.althaus.dev.atp12_diceroller.model.DiceRoll
import com.althaus.dev.atp12_diceroller.repository.DiceRollRepository
import kotlinx.coroutines.launch

class DiceViewModel(private val repository: DiceRollRepository) : ViewModel() {

    val allDiceRolls: LiveData<List<DiceRoll>> = repository.getAllDiceRolls()

    private var dado = Dice(6)

    private var _resultadoDado1 = MutableLiveData<Int>()
    private var _resultadoDado2 = MutableLiveData<Int>()

    val resultadoDado1: LiveData<Int> = _resultadoDado1
    val resultadoDado2: LiveData<Int> = _resultadoDado2

    fun rollDice() {
        val resultadoDado1 = dado.rollDice()
        val resultadoDado2 = dado.rollDice()

        _resultadoDado1.value = resultadoDado1
        _resultadoDado2.value = resultadoDado2

        val diceRoll = DiceRoll(dado1 = resultadoDado1, dado2 = resultadoDado2)

        viewModelScope.launch {
            repository.insertDiceRoll(diceRoll)

        }
    }
}