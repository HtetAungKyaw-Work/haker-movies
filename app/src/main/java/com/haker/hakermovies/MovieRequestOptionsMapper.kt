package com.haker.hakermovies

import com.haker.hakermovies.Constants.Filter.INCLUDE_ADULT
import com.haker.hakermovies.Constants.Filter.SORT_BY
import com.haker.hakermovies.Constants.Filter.WITH_GENRES
import com.haker.hakermovies.data.model.FilterResult
import javax.inject.Inject

class MovieRequestOptionsMapper @Inject constructor() : Mapper<FilterResult?, Map<String, String>> {
    override fun map(input: FilterResult?): Map<String, String> = buildMap {
        val filterState = input ?: FilterResult()
        val sortBy = "${filterState.sortBy}.desc"
        put(SORT_BY, sortBy)
        val includeAdult = filterState.includeAdult.toString()
        put(INCLUDE_ADULT, includeAdult)
        if (filterState.selectedGenreList.isNotEmpty()) {
            val selectedGenreIds = filterState.selectedGenreList.map { it.id }.joinToString("|")
            put(WITH_GENRES, selectedGenreIds)
        }
    }

}
