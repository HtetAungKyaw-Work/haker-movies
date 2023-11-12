package com.haker.hakermovies.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haker.hakermovies.Constants
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.data.model.local.MovieOffline

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query(Constants.Queries.DELETE_FAVORITE)
    suspend fun deleteFavorite(id: Int)

    @Query(Constants.Queries.IS_FAVORITE)
    suspend fun isFavorite(id: Int): Boolean

    @Query(Constants.Queries.GET_FAVORITES)
    suspend fun getFavorites(): List<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(offline: MovieOffline)

    @Query(Constants.Queries.GET_MOVIES)
    suspend fun getMovies(): List<MovieOffline>

    @Query(Constants.Queries.DELETE_MOVIES)
    suspend fun deleteMovies()
}