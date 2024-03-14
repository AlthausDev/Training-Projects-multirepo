package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.databinding.FragmentReservationConfirmationBinding
import com.althaus.dev.project04_cartelera.ui.viewModel.ReservationConfirmationViewModel

class ReservationConfirmationFragment : Fragment() {

    private var _binding: FragmentReservationConfirmationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReservationConfirmationViewModel by viewModels()

    private lateinit var movieTitle: String
    private var numberOfTickets: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = ReservationConfirmationFragmentArgs.fromBundle(requireArguments())
        val movieTitle = args.movieTitle
        val numberOfTickets = args.numberOfTickets

        binding.movieTitleTextView.text = movieTitle
        binding.numberOfTicketsTextView.text = numberOfTickets.toString()

        val ticketPrice = calculateTicketPrice()
        val totalPrice = numberOfTickets * ticketPrice
        binding.totalPriceTextView.text = getString(R.string.total_price_format, totalPrice)

        binding.confirmButton.setOnClickListener {
            viewModel.confirmReservation(Reservation(0, movieTitle, numberOfTickets))
        }

        viewModel.reservationConfirmed.observe(viewLifecycleOwner) { confirmed ->
            if (confirmed) {
                // Handle reservation confirmation
                findNavController().navigate(R.id.action_reservationConfirmationFragment_to_movieListFragment)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            // Handle error message
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateTicketPrice(): Double {
        return 7.0 // Fixed ticket price for now
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}