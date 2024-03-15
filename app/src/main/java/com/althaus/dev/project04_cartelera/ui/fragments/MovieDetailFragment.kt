package com.althaus.dev.project04_cartelera.ui.fragments

import MovieDetailViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavDirections
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.databinding.FragmentMovieDetailBinding
import com.althaus.dev.project04_cartelera.ui.fragments.MovieDetailFragmentArgs
import com.squareup.picasso.Picasso
import com.althaus.dev.project04_cartelera.ui.fragments.MovieDetailFragmentDirections

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
        arguments?.let {
            val args = MovieDetailFragmentArgs.fromBundle(it)
            movie = args.movie
        }

        viewModel.setMovie(movie)

        // Mostrar los detalles de la película en el layout
        binding.apply {
            Picasso.get().load(movie.posterPath).into(posterImageView)
            titleTextView.text = movie.title
            releaseDateTextView.text = "Release Date: ${movie.releaseDate}"
            genreTextView.text = "Genres: ${movie.genreIds.joinToString(", ")}"
            overviewTextView.text = movie.overview
            popularityTextView.text = "Popularity: ${movie.popularity}"
            voteAverageTextView.text = "Vote Average: ${movie.voteAverage}"
            voteCountTextView.text = "Vote Count: ${movie.voteCount}"
        }

        // Configurar clics en los botones
        binding.continueButton.setOnClickListener {
            viewModel.onContinueClicked()
        }

        binding.cancelButton.setOnClickListener {
            viewModel.onCancelClicked()
        }

        // Observar la navegación
        observeNavigation()

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


