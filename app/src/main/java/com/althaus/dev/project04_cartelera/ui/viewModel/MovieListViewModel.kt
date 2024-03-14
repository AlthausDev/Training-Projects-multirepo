package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class MovieListViewModel : ViewModel() {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private var fetchMoviesJob: Job? = null

    init {
        loadMovies()
    }

    private fun loadMovies() {
        _loading.value = true
        fetchMoviesJob = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.movieApiService.getNowPlayingMovies()
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    _movies.postValue(movies)
                } else {
                    _error.postValue("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: IOException) {
                _error.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Error inesperado: ${e.message}")
            } finally {
                _loading.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchMoviesJob?.cancel()
    }
}
