package com.haker.hakermovies.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haker.hakermovies.Resource
import com.haker.hakermovies.domain.model.MovieUI
import com.haker.hakermovies.use_case.GetPopularMoviesUseCase
import com.haker.hakermovies.use_case.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase)
    : ViewModel() {

    private val _upcomingMovies =
        MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val upcomingMovies
        get() = _upcomingMovies.asStateFlow()

    private val _popularMovies =
        MutableStateFlow<PagingData<MovieUI>>(PagingData.empty())
    val popularMovies
        get() = _popularMovies.asStateFlow()

    init {
        getUpcomingMovies()
        getPopularMovies()
    }

    private fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesUseCase().cachedIn(viewModelScope).collectLatest {
            _popularMovies.emit(it)

        }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        getUpcomingMoviesUseCase().cachedIn(viewModelScope).collectLatest {
            _upcomingMovies.emit(it)

        }
    }
}