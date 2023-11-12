package com.haker.hakermovies.data.source.local


import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.data.model.local.MovieOffline
import com.haker.hakermovies.domain.source.DataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    DataSource.Local {
    override suspend fun addFavorite(favorite: Favorite) = movieDao.addFavorite(favorite)

    override suspend fun removeFavorite(id: Int) = movieDao.deleteFavorite(id)

    override suspend fun isFavorite(id: Int): Boolean = movieDao.isFavorite(id)

    override suspend fun getFavorites(): List<Favorite> = movieDao.getFavorites()
    override suspend fun addMovie(movieOffline: MovieOffline) = movieDao.addMovie(movieOffline)

    override suspend fun getMovies(): List<MovieOffline> = movieDao.getMovies()

    override suspend fun deleteMovies() = movieDao.deleteMovies()

}