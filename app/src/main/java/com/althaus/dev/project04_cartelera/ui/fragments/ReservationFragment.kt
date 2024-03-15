package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.databinding.FragmentReservationBinding

class ReservationFragment : Fragment() {

    private var _binding: FragmentReservationBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveArguments()

        // Mostrar el título de la película en algún TextView en el formulario de reserva
        binding.movieTitleTextView.text = movie.title
        binding.numberOfTicketsEditText.setText("1")

        // Manejar la lógica para calcular el precio de la reserva
        binding.confirmReservationButton.setOnClickListener {
            val numberOfTickets = binding.numberOfTicketsEditText.text.toString().toIntOrNull() ?: 0
            val ticketPrice = calculateTicketPrice(numberOfTickets)
            val totalPrice = numberOfTickets * ticketPrice
            binding.totalPriceTextView.text = getString(R.string.total_price_format, totalPrice)
            val action = ReservationFragmentDirections.actionMovieReservationFragmentToReservationConfirmationFragment(movie, numberOfTickets)
            findNavController().navigate(action)
        }

        binding.cancelReservationButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.increaseTicketsButton.setOnClickListener {
            val currentTickets = binding.numberOfTicketsEditText.text.toString().toIntOrNull() ?: 0
            binding.numberOfTicketsEditText.setText((currentTickets + 1).toString())
        }

        // Botón para decrementar el número de boletos
        binding.decreaseTicketsButton.setOnClickListener {
            var currentTickets = binding.numberOfTicketsEditText.text.toString().toIntOrNull() ?: 1
            currentTickets = maxOf(currentTickets - 1, 1)
            binding.numberOfTicketsEditText.setText(currentTickets.toString())
        }

        binding.numberOfTicketsEditText.addTextChangedListener {
            val numberOfTickets = it.toString().toIntOrNull() ?: 1
            val ticketPrice = calculateTicketPrice(numberOfTickets)
            val totalPrice = numberOfTickets * ticketPrice
            binding.totalPriceTextView.text = getString(R.string.total_price_format, totalPrice)
        }
    }

    private fun retrieveArguments() {
        val args = ReservationFragmentArgs.fromBundle(requireArguments())
        movie = args.movie
    }

    private fun calculateTicketPrice(numberOfTickets: Int): Double {
        val basePrice = 7.0
        return basePrice
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
