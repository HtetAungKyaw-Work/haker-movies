package com.haker.hakermovies

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "9930eeff808ba4e98158f26b3ec3459a"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"
    const val STARTING_PAGE = 1
    const val NETWORK_PAGE_SIZE = 10

    object Endpoints {
        const val GET_POPULAR_MOVIES = "movie/popular"
        const val GET_UPCOMING_MOVIES = "movie/upcoming"
        const val GET_MOVIE_DETAILS = "movie/{movie_id}"
    }

    object SortBy {
        const val POPULARITY = "popularity"
        const val VOTE_COUNT = "vote_count"
        const val VOTE_AVERAGE = "vote_average"
        const val RELEASE_DATE = "release_date"
    }

    object Filter {
        const val SORT_BY = "sort_by"
        const val INCLUDE_ADULT = "include_adult"
        const val WITH_GENRES = "with_genres"
    }

    object Arguments {
        const val ID = "id"
        const val MEDIA_TYPE = "mediaType"
        const val FILTER_RESULT = "filterResult"
        const val POP_UP = "popUp"
        const val DELETED_POSITION = "deletedPosition"

    }

    object Queries {
        const val DELETE_FAVORITE = "DELETE FROM favorites WHERE id = :id"
        const val IS_FAVORITE = "SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)"
        const val GET_FAVORITES = "SELECT * FROM favorites ORDER BY addDate DESC"

        const val GET_MOVIES = "SELECT * FROM movies ORDER BY releaseDate DESC"
        const val DELETE_MOVIES = "DELETE FROM favorites"
    }
}