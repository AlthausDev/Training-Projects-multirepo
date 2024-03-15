package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.databinding.FragmentReservationConfirmationBinding
import com.althaus.dev.project04_cartelera.ui.viewModel.ReservationConfirmationViewModel

class ReservationConfirmationFragment : Fragment() {

    private var _binding: FragmentReservationConfirmationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReservationConfirmationViewModel by viewModels()
    private lateinit var movie: Movie


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveArguments()

        val args = ReservationConfirmationFragmentArgs.fromBundle(requireArguments())
        val numberOfTickets = args.numberOfTickets

        binding.movieTitleTextView.text = movie.title
        binding.numberOfTicketsTextView.text = numberOfTickets.toString()

        //val ticketPrice = calculateTicketPrice()
       // val totalPrice = numberOfTickets * ticketPrice
        //val totalPriceText = getString(R.string.total_price_format, totalPrice)
        //binding.totalPriceTextView.text = totalPriceText

//        binding.confirmButton.setOnClickListener {
//            viewModel.confirmReservation(Reservation(0, movieTitle, numberOfTickets))
//        }

        viewModel.reservationConfirmed.observe(viewLifecycleOwner) { confirmed ->
            if (confirmed) {
                findNavController().navigate(R.id.action_reservationConfirmationFragment_to_reservationListFragment)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun retrieveArguments() {
        val args = ReservationFragmentArgs.fromBundle(requireArguments())
        movie = args.movie
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
