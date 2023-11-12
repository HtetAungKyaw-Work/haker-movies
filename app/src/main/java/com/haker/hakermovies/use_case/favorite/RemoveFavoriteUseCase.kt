package com.haker.hakermovies.use_case.favorite

import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(id: Int) {
       movieRepository.removeFavorite(id)
    }
}