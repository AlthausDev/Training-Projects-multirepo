package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.databinding.FragmentMovieDetailBinding
import com.althaus.dev.project04_cartelera.ui.viewModel.MovieDetailViewModel
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        // Recuperar la película de los argumentos seguros
        val movie = arguments?.let {
            val args = MovieDetailFragmentArgs.fromBundle(it)
            args.movie
        }

        // Verificar que la película no sea nula y que la URL de la imagen sea válida antes de cargarla
        movie?.let {
            if (!it.posterPath.isNullOrEmpty()) {
                val imageUrl = "https://image.tmdb.org/t/p/w500${it.posterPath}"
                Picasso.get().load(imageUrl).into(binding.posterImageView)
            }
        }

        // Observar la navegación
        observeNavigation()

        // Configurar clics en los botones
        binding.continueButton.setOnClickListener {
            viewModel.onContinueClicked()
        }

        binding.cancelButton.setOnClickListener {
            viewModel.onCancelClicked()
        }
    }

    private fun observeNavigation() {
        viewModel.navigateToReservation.observe(viewLifecycleOwner) { navigateToReservation ->
            if (navigateToReservation) {
                // Navegar a la pantalla de reserva
                navigateToReservation()
                viewModel.onNavigationComplete()
            }
        }

        viewModel.navigateToMovieList.observe(viewLifecycleOwner) { navigateToMovieList ->
            if (navigateToMovieList) {
                // Volver a la lista de películas
                navigateToMovieList()
                viewModel.onNavigationComplete()
            }
        }
    }

    private fun navigateToReservation() {
        val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMovieReservationFragment(movie)
        findNavController().navigate(action)
    }

    private fun navigateToMovieList() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
