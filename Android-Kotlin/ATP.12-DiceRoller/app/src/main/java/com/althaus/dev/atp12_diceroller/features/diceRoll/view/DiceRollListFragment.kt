package com.althaus.dev.atp12_diceroller.features.diceRoll.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.althaus.dev.atp12_diceroller.databinding.FragmentDiceRollListBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel

/**
 * A fragment representing a list of Items.
 */
class DiceRollListFragment : Fragment() {

    private var columnCount = 1
    private lateinit var binding: FragmentDiceRollListBinding
    private lateinit var viewModel: DiceViewModel
    private lateinit var adapter: DiceRollListAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
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

        // Inicializar recyclerView
        recyclerView = binding.rollList

        viewModel.allDiceRolls.observe(viewLifecycleOwner) { diceRolls ->
            adapter.submitList(diceRolls) {
                recyclerView.scrollToPosition(0)
            }
        }
    }

    companion object {
        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            DiceRollListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

}