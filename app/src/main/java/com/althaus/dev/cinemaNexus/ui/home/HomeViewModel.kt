package com.althaus.dev.cinemaNexus.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.model.Movie
import com.althaus.dev.cinemaNexus.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    fun fetchMovies() {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading
            try {
                val movies = movieRepository.getNowPlayingMovies()
                if (movies.isNotEmpty()) {
                    _homeState.value = HomeState.Success(movies)
                } else {
                    _homeState.value = HomeState.Empty
                }
            } catch (e: Exception) {
                _homeState.value = HomeState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
