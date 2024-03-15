package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaus.dev.project04_cartelera.databinding.FragmentReservationListBinding
import com.althaus.dev.project04_cartelera.ui.adapters.ReservationListAdapter
import com.althaus.dev.project04_cartelera.ui.viewModel.ReservationListViewModel

class ReservationListFragment : Fragment() {

    private var _binding: FragmentReservationListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReservationListViewModel by viewModels()
    private lateinit var reservationAdapter: ReservationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val userId = 123 // Aquí debes proporcionar el ID del usuario actual
        viewModel.loadReservations(userId)
        observeReservations()
    }

    private fun setupRecyclerView() {
        reservationAdapter = ReservationListAdapter()
        binding.reservationRecyclerView.apply {
            adapter = reservationAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeReservations() {
        viewModel.reservations.observe(viewLifecycleOwner) { reservations ->
            reservations?.let {
                reservationAdapter.submitList(it)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
