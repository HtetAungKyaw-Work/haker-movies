package com.haker.hakermovies.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.domain.mapper.toMovieDetailsUI
import com.haker.hakermovies.domain.mapper.toMovieUI
import com.haker.hakermovies.domain.model.MovieDetailsUI
import com.haker.hakermovies.domain.model.MovieUI
import com.haker.hakermovies.domain.repository.MovieRepository
import com.haker.hakermovies.domain.source.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: DataSource.Remote,
    private val local: DataSource.Local
) :
    MovieRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieUI>> = flow {
        remote.getPopularMovies().map { pagingData ->
            pagingData.map { it.toMovieUI() }
        }.collect {
            emit(it)
        }
    }

    override fun getUpcomingMovies(): Flow<PagingData<MovieUI>> = flow {
        remote.getUpcomingMovies().map { pagingData ->
            pagingData.map { it.toMovieUI() }
        }.collect {
            emit(it)
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailsUI>> = flow {
        emit(Resource.Loading)
        try {
            val response = remote.getMovieDetails(movieId).toMovieDetailsUI()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override suspend fun addFavorite(favorite: Favorite) {
        local.addFavorite(favorite)
    }

    override suspend fun removeFavorite(id: Int) {
        local.removeFavorite(id)
    }

    override fun isFavorite(id: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            val response = local.isFavorite(id)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getFavorites(): Flow<Resource<List<Favorite>>> = flow {
        emit(Resource.Loading)
        try {
            val response = local.getFavorites()
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

}