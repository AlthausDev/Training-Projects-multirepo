package com.althaus.dev.project04_cartelera.ui.fragments

import ReservationConfirmationViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.base.App
import com.althaus.dev.project04_cartelera.data.dao.ReservationDao
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
import com.althaus.dev.project04_cartelera.data.repository.impl.ReservationRepositoryImpl
import com.althaus.dev.project04_cartelera.databinding.FragmentReservationConfirmationBinding

class ReservationConfirmationFragment : Fragment() {

    private var _binding: FragmentReservationConfirmationBinding? = null
    private val binding get() = _binding!!


    private lateinit var movie: Movie
    private var numberOfTickets: Int = 0
    private var totalPrice: Double = 0.0

    private lateinit var viewModel: ReservationConfirmationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reservationDao = App.database.reservationDao()
        val reservationRepository = ReservationRepositoryImpl(reservationDao)

        viewModel = ViewModelProvider(this).get(ReservationConfirmationViewModel::class.java)
       viewModel.initRepository(reservationRepository, reservationDao)



        retrieveArguments()

        binding.movieTitleTextView.text = movie.title

        val totalTicketsText = getString(R.string.total_tickets_format, numberOfTickets)
        binding.numberOfTicketsTextView.text = totalTicketsText

        val totalPriceText = getString(R.string.total_price_format, totalPrice)
        binding.totalPriceTextView.text = totalPriceText

        binding.confirmButton.setOnClickListener {
            viewModel.confirmReservationAndSaveToDatabase(Reservation(0, movie.title, numberOfTickets, totalPrice))
        }


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
        val args = ReservationConfirmationFragmentArgs.fromBundle(requireArguments())
        movie = args.movie
        numberOfTickets = args.numberOfTickets
        totalPrice = (args.numberOfTickets * 7).toDouble()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
