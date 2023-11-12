package com.haker.hakermovies.data.source.remote

import com.haker.hakermovies.Constants
import com.haker.hakermovies.data.model.remote.MovieDetailsResponse
import com.haker.hakermovies.data.model.remote.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.Endpoints.GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesResponse

    @GET(Constants.Endpoints.GET_UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(@Query("page") page: Int): MoviesResponse

    @GET(Constants.Endpoints.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailsResponse
}