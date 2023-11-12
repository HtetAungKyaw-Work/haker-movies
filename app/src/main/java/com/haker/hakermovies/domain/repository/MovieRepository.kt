package com.haker.hakermovies.domain.repository

import androidx.paging.PagingData
import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.domain.model.MovieDetailsUI
import com.haker.hakermovies.domain.model.MovieUI
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieUI>>
    fun getUpcomingMovies(): Flow<PagingData<MovieUI>>

    fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailsUI>>

    suspend fun addFavorite(favorite: Favorite)
    suspend fun removeFavorite(id: Int)
    fun isFavorite(id: Int): Flow<Resource<Boolean>>
    fun getFavorites(): Flow<Resource<List<Favorite>>>
}