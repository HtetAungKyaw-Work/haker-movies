package com.haker.hakermovies.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haker.hakermovies.Constants.STARTING_PAGE
import com.haker.hakermovies.MovieEnum
import com.haker.hakermovies.MovieRequestOptionsMapper
import com.haker.hakermovies.data.model.FilterResult
import com.haker.hakermovies.data.model.remote.Movie
import com.haker.hakermovies.data.source.remote.MovieService
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val movieService: MovieService,
    private val movieEnum: MovieEnum,
    private val searchQuery: String = "",
    movieRequestOptionsMapper: MovieRequestOptionsMapper,
    filterResult: FilterResult? = null,
    private val includeAdult: Boolean = false,
    private val movieId: Int = 0

) : PagingSource<Int, Movie>() {
    private val options = movieRequestOptionsMapper.map(filterResult)


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val response =
                when (movieEnum) {
                    MovieEnum.POPULAR_MOVIES -> {
                        movieService.getPopularMovies(page)
                    }
                    MovieEnum.UPCOMING_MOVIES -> {
                        movieService.getUpcomingMovies(page)
                    }
                }

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == STARTING_PAGE) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }
}