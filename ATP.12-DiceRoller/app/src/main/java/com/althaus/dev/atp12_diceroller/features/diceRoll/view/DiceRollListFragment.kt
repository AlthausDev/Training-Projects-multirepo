package com.althaus.dev.atp12_diceroller.features.diceRoll.view

import DiceRollListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.althaus.dev.atp12_diceroller.databinding.FragmentDiceRollListBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel

/**
 * A fragment representing a list of Items.
 */
class DiceRollListFragment : Fragment() {

    private var columnCount = 4
    private lateinit var binding: FragmentDiceRollListBinding
    private lateinit var viewModel: DiceViewModel
    private lateinit var adapter: DiceRollListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            //columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiceRollListBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(DiceViewModel::class.java)

        adapter = DiceRollListAdapter()

        val gridLayoutManager = GridLayoutManager(requireContext(), columnCount)
        binding.rollList.layoutManager = gridLayoutManager
        binding.rollList.adapter = adapter


        viewModel.allDiceRolls.observe(viewLifecycleOwner) { diceRolls ->
            adapter.submitList(diceRolls)
        }
    }
}