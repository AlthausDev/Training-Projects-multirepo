package com.samuelaa.pmdm.diceroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

    fun twoDices(numCaras: Int): ViewModel {
            return TwoDiceViewModel(numCaras)
    }
}