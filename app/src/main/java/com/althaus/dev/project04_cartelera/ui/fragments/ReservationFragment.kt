
package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Manejar la lógica para calcular el precio de la reserva
        binding.confirmReservationButton.setOnClickListener {
            val numberOfTickets = binding.numberOfTicketsEditText.text.toString().toIntOrNull() ?: 0
            val ticketPrice = calculateTicketPrice(numberOfTickets)
            val totalPrice = numberOfTickets * ticketPrice
            //binding.totalPriceTextView.text = getString(R.string.total_price_format, totalPrice)
        }
    }

    private fun retrieveArguments() {
        val args = ReservationFragmentArgs.fromBundle(requireArguments())
        movie = args.movie
    }

    private fun calculateTicketPrice(numberOfTickets: Int): Double {
        val basePrice = 7.0 // Precio base por ticket
        val totalPrice = basePrice * numberOfTickets
        return totalPrice
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}