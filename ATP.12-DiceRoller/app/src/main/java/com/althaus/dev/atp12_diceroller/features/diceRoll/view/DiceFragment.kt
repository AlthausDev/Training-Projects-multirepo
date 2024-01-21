package com.althaus.dev.atp12_diceroller.features.diceRoll.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.althaus.dev.atp12_diceroller.R
import com.althaus.dev.atp12_diceroller.databinding.FragmentDiceBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel

class DiceFragment : Fragment() {

    private lateinit var binding: FragmentDiceBinding
    private lateinit var sharedViewModel: DiceViewModel
    private var resultadoTotal = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(DiceViewModel::class.java)



        sharedViewModel.resultadoDado1.observe(viewLifecycleOwner) { resultado ->
            val drawableResource = getDiceImage(resultado)
            binding.dado1.setImageResource(drawableResource)

            resultadoTotal += resultado
        }

        sharedViewModel.resultadoDado2.observe(viewLifecycleOwner) { resultado ->
            val drawableResource = getDiceImage(resultado)
            binding.dado2.setImageResource(drawableResource)

            resultadoTotal += resultado
            showToast()
        }
    }

    private fun showToast() {
        val toastMessage = "Resultado: $resultadoTotal"
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
        resultadoTotal = 0
    }

    private fun getDiceImage(resultado: Int): Int {
        val drawableResource = when (resultado) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return drawableResource
    }
}