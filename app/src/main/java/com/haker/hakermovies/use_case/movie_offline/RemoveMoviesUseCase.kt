package com.haker.hakermovies.use_case.movie_offline

import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() {
       movieRepository.deleteMovies()
    }
}