package com.haker.hakermovies.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.MovieOffline
import com.haker.hakermovies.domain.model.MovieUI
import com.haker.hakermovies.use_case.GetPopularMoviesUseCase
import com.haker.hakermovies.use_case.GetUpcomingMoviesUseCase
import com.haker.hakermovies.use_case.movie_offline.AddMovieUseCase
import com.haker.hakermovies.use_case.movie_offline.DeleteMoviesUseCase
import com.haker.hakermovies.use_case.movie_offline.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val addMovieUseCase: AddMovieUseCase,
    private val deleteMoviesUseCase: DeleteMoviesUseCase,
    private val getMoviesUseCase: GetMoviesUseCase)
    : ViewModel() {

    private val _upcomingMovies =
        MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val upcomingMovies
        get() = _upcomingMovies.asStateFlow()

    private val _popularMovies =
        MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val popularMovies
        get() = _popularMovies.asStateFlow()

    private val _offlineMovies =
        MutableSharedFlow<Resource<List<MovieOffline>>>()
    val offlineMovies
        get() = _offlineMovies.asSharedFlow()

    init {
        getUpcomingMovies()
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesUseCase().cachedIn(viewModelScope).collectLatest {
            _popularMovies.emit(it)
            Log.i("getPopularMovies", it.toString())
        }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        getUpcomingMoviesUseCase().cachedIn(viewModelScope).collectLatest {
            _upcomingMovies.emit(it)

        }
    }

    fun addMovie(movieOffline: MovieOffline) = viewModelScope.launch {
        addMovieUseCase(movieOffline)
    }

    fun deleteMovies() = viewModelScope.launch {
        deleteMoviesUseCase()
    }

    fun getMovies() = viewModelScope.launch {
        getMoviesUseCase().collectLatest {
            _offlineMovies.emit(it)
        }
    }
}