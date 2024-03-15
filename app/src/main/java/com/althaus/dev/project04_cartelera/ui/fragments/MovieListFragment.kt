package com.althaus.dev.project04_cartelera.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.network.RetrofitClient
import com.althaus.dev.project04_cartelera.databinding.FragmentMovieListBinding
import com.althaus.dev.project04_cartelera.ui.adapters.MovieAdapter
import com.althaus.dev.project04_cartelera.ui.viewModel.MovieListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var navController: NavController
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(viewModel)
        binding.recyclerView.adapter = movieAdapter

        // Inicializar NavController correctamente
        navController = findNavController()

        // Observar el evento navigateToMovieDetail del ViewModel
        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                navigateToMovieDetail(movie)
                viewModel.onMovieNavigated() // Limpiar la acción de navegación para evitar navegaciones múltiples
            }
        })

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
                    // Manejar el caso en que la respuesta no sea exitosa
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Error al cargar las películas: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Log.e("MovieListFragment", "Error al cargar las películas", e)
            }
        }
    }

    // Navegar al detalle de la película
    private fun navigateToMovieDetail(movie: Movie) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
        navController.navigate(action)
    }
}
