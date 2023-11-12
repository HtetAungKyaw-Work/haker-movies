package com.haker.hakermovies.data.model

import android.os.Parcelable
import com.haker.hakermovies.Constants
import com.haker.hakermovies.MediaTypeEnum
import com.haker.hakermovies.data.model.remote.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterResult(
    var type: MediaTypeEnum = MediaTypeEnum.MOVIE,
    var selectedGenreList: MutableList<Genre> = mutableListOf(),
    var sortBy: String = Constants.SortBy.POPULARITY,
    var includeAdult: Boolean = false,
    var genreList: List<Genre> = emptyList()
) : Parcelable
