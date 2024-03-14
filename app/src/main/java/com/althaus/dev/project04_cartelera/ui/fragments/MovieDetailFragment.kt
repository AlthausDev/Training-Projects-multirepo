package com.althaus.dev.project04_cartelera.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.althaus.dev.project04_cartelera.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieTitle: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar el título de la película de los argumentos seguros
        arguments?.let {
            val args = MovieDetailFragmentArgs.fromBundle(it)
            movieTitle = args.movieTitle
        }

        // Mostrar el título de la película en algún TextView en el fragmento
        binding.movieTitleTextView.text = movieTitle
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
