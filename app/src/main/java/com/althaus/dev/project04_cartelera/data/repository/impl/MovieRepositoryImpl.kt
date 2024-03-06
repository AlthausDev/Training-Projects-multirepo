package com.althaus.dev.project04_cartelera.data.repository.impl

import com.althaus.dev.project04_cartelera.data.dao.MovieDao
import com.althaus.dev.project04_cartelera.data.model.Movie
import com.althaus.dev.project04_cartelera.data.repository.MovieRepository

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun getMovieById(movieId: Int): Movie? {
        return movieDao.getMovieById(movieId)
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }
}
