package com.haker.hakermovies.use_case.movie_offline

import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.MovieOffline
import com.haker.hakermovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieOffline>>> = movieRepository.getMovies()
}