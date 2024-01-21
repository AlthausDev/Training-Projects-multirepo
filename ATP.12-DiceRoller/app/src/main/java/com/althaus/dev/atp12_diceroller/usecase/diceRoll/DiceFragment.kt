package com.althaus.dev.atp12_diceroller.usecase.diceRoll

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.althaus.dev.atp12_diceroller.R
import com.althaus.dev.atp12_diceroller.usecase.onboarding.diceRoll.DiceViewModel

class DiceFragment : Fragment() {

    companion object {
        fun newInstance() = DiceFragment()
    }

    private lateinit var viewModel: DiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}