package com.haker.hakermovies.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieOffline (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val genres: String,
    val originalTitle: String,
    val posterPath: String?,
    val backdropPath: String?,
    val title: String,
    val voteAverage: Double,
    var isFavorite: Boolean = false,
    val releaseDate: String?,
    val overview: String?,
    val type: Int
)