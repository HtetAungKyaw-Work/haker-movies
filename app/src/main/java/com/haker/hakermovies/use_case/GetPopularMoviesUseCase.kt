package com.haker.hakermovies.use_case

import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke() = movieRepository.getPopularMovies()
}