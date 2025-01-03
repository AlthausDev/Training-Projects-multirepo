package com.althaus.dev.cinemaNexus.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Representa los diferentes estados de la pantalla de inicio.
 */
sealed class HomeState {
    object Loading : HomeState()
    data class Success(val movies: List<Movie>) : HomeState()
    object Empty : HomeState()
    data class Error(val message: String) : HomeState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState

    init {
        fetchMovies()
    }

    /**
     * Obtiene la lista de películas en cartelera y actualiza el estado.
     */
    fun fetchMovies() {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            try {
                val movies = movieRepository.getNowPlayingMovies()
                if (movies.isNotEmpty()) {
                    _homeState.value = HomeState.Success(movies)
                } else {
                    println("Películas no disponibles") // Depuración
                    _homeState.value = HomeState.Empty
                }
            } catch (e: Exception) {
                _homeState.value = HomeState.Error(e.message ?: "Error desconocido")
            }
        }
    }


    /**
     * Busca películas por término y actualiza el estado.
     *
     * @param query Término de búsqueda.
     */
    fun searchMovies(query: String) {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            try {
                val movies = movieRepository.searchMovies(query)
                if (movies.isNotEmpty()) {
                    _homeState.value = HomeState.Success(movies)
                } else {
                    _homeState.value = HomeState.Empty
                }
            } catch (e: Exception) {
                _homeState.value = HomeState.Error(mapError(e))
            }
        }
    }

    /**
     * Mapea excepciones a mensajes de error más descriptivos.
     */
    private fun mapError(e: Exception): String {
        return e.message ?: "Error desconocido. Por favor, intenta de nuevo más tarde."
    }
}
