package com.haker.hakermovies.use_case.favorite

import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(id: Int) = movieRepository.isFavorite(id)
}