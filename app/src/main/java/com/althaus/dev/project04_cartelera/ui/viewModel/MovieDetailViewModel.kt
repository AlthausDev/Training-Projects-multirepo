package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaus.dev.project04_cartelera.data.model.Movie

class MovieDetailViewModel : ViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> get() = _movie

    fun setMovie(movie: Movie) {
        _movie.value = movie
    }
}
