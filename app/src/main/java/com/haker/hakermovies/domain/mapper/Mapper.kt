package com.haker.hakermovies.domain.mapper

import com.haker.hakermovies.data.model.remote.Movie
import com.haker.hakermovies.data.model.remote.MovieDetailsResponse
import com.haker.hakermovies.domain.model.MovieDetailsUI
import com.haker.hakermovies.domain.model.MovieUI

fun List<Movie>.toMovieUI() = map {
    MovieUI(
        id = it.id,
        title = it.title,
        posterPath = it.posterPath,
        backdropPath = it.backdropPath,
        voteAverage = it.voteAverage,
        originalTitle = it.originalTitle,
        adult = it.adult,
        releaseDate = it.releaseDate
    )
}

fun Movie.toMovieUI() =
    MovieUI(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        originalTitle = originalTitle,
        adult = adult,
        releaseDate = releaseDate
    )

fun MovieDetailsResponse.toMovieDetailsUI() =
    MovieDetailsUI(
        backdropPath = backdropPath,
        genres = genres,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        homepage = homepage
    )
