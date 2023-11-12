package com.haker.hakermovies.use_case.favorite

import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Favorite>>> = movieRepository.getFavorites()
}