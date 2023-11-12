package com.haker.hakermovies.domain.source

import androidx.paging.PagingData
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.data.model.remote.Movie
import com.haker.hakermovies.data.model.remote.MovieDetailsResponse
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface DataSource {

    interface Remote {
        suspend fun getPopularMovies(): Flow<PagingData<Movie>>
        suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>
        suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
    }

    interface Local {
        suspend fun addFavorite(favorite: Favorite)
        suspend fun removeFavorite(id: Int)
        suspend fun isFavorite(id: Int): Boolean
        suspend fun getFavorites(): List<Favorite>
    }
}