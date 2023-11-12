package com.haker.hakermovies.use_case.favorite

import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(favorite: Favorite) {
        movieRepository.addFavorite(favorite)
    }
}