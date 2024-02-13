package com.althaus.dev.atp12_diceroller.features.common.controls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.althaus.dev.atp12_diceroller.databinding.FragmentButtonBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel

class ButtonFragment : Fragment() {

    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: DiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(DiceViewModel::class.java)

        binding.btnRollDice.setOnClickListener {
            sharedViewModel.rollDice()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}