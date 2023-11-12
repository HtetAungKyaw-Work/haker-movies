package com.haker.hakermovies.use_case

import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int) = repository.getMovieDetails(movieId)
}