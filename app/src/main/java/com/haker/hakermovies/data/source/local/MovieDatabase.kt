package com.haker.hakermovies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.data.model.local.MovieOffline

@Database(
    entities = [Favorite::class, MovieOffline::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}