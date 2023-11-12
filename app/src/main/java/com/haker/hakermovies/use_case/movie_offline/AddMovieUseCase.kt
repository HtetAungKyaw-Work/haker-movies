package com.haker.hakermovies.use_case.movie_offline

import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.data.model.local.MovieOffline
import com.haker.hakermovies.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieOffline: MovieOffline) {
        movieRepository.addMovie(movieOffline)
    }
}