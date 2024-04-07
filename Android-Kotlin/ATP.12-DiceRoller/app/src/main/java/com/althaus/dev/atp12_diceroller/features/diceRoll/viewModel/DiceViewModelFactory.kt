package com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.althaus.dev.atp12_diceroller.repository.DiceRollRepository

class DiceViewModelFactory(private val repository: DiceRollRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
