package com.althaus.dev.project04_cartelera.ui.fagments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.network.RetrofitClient
import com.althaus.dev.project04_cartelera.databinding.FragmentMovieListBinding
import com.althaus.dev.project04_cartelera.ui.adapters.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter


        val movieApiService = RetrofitClient.movieApiService
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getNowPlayingMovies()
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    withContext(Dispatchers.Main) {
                        if (isAdded) {
                            movieAdapter.submitList(movies)
                        }
                    }
                } else {

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error al cargar las películas", Toast.LENGTH_SHORT).show()
                }
                Log.e("MovieListFragment", "Error al cargar las películas", e)
            }
        }
    }
}
