package com.haker.hakermovies.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.haker.hakermovies.Constants.NETWORK_PAGE_SIZE
import com.haker.hakermovies.MovieEnum
import com.haker.hakermovies.MovieRequestOptionsMapper
import com.haker.hakermovies.data.model.remote.Movie
import com.haker.hakermovies.data.model.remote.MovieDetailsResponse
import com.haker.hakermovies.data.paging_source.MoviePagingSource
import com.haker.hakermovies.domain.source.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieRequestOptionsMapper: MovieRequestOptionsMapper
) :
    DataSource.Remote {
    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> = Pager (
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = 2,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(
                movieService,
                MovieEnum.POPULAR_MOVIES,
                movieRequestOptionsMapper = movieRequestOptionsMapper
            )
        }
    ).flow

    override suspend fun getUpcomingMovies(): Flow<PagingData<Movie>> = Pager (
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = 2,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            jumpThreshold = Int.MIN_VALUE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            MoviePagingSource(
                movieService,
                MovieEnum.UPCOMING_MOVIES,
                movieRequestOptionsMapper = movieRequestOptionsMapper
            )
        }
    ).flow

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse =
        movieService.getMovieDetails(movieId)
}