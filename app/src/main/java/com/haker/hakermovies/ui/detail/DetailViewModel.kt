package com.haker.hakermovies.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haker.hakermovies.Constants
import com.haker.hakermovies.MediaTypeEnum
import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.domain.model.MovieDetailsUI
import com.haker.hakermovies.use_case.GetMovieDetailsUseCase
import com.haker.hakermovies.use_case.favorite.AddFavoriteUseCase
import com.haker.hakermovies.use_case.favorite.IsFavoriteUseCase
import com.haker.hakermovies.use_case.favorite.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _movieDetails = MutableSharedFlow<Resource<MovieDetailsUI>>()
    val movieDetails
        get() = _movieDetails.asSharedFlow()

    private var _isFavorite = MutableSharedFlow<Resource<Boolean>>()
    val isFavorite
        get() = _isFavorite.asSharedFlow()

    fun getDetails() {
        savedStateHandle.get<Int>(Constants.Arguments.ID)?.let { id ->
            savedStateHandle.get<MediaTypeEnum>(Constants.Arguments.MEDIA_TYPE)?.let { mediaType ->
                getMovieDetails(id)

                isFavorite(id)

            }
        } ?: throw IllegalArgumentException("Missing id or media type")
    }

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        getMovieDetailsUseCase(movieId).collectLatest {
            _movieDetails.emit(it)
        }
    }

    fun addFavorite(favorite: Favorite) = viewModelScope.launch {
        addFavoriteUseCase(favorite)
    }

    fun removeFavorite(id: Int) = viewModelScope.launch {
        removeFavoriteUseCase(id)
    }

    fun isFavorite(id: Int) = viewModelScope.launch {
        isFavoriteUseCase(id).collectLatest {
            _isFavorite.emit(it)
        }
    }
}