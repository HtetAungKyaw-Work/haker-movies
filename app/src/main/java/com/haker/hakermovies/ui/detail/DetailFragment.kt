package com.haker.hakermovies.ui.detail

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.haker.hakermovies.MediaTypeEnum
import com.haker.hakermovies.R
import com.haker.hakermovies.Resource
import com.haker.hakermovies.data.model.local.Favorite
import com.haker.hakermovies.databinding.FragmentDetailBinding
import com.haker.hakermovies.format
import com.haker.hakermovies.getReformatDate
import com.haker.hakermovies.loadImage
import com.haker.hakermovies.showToast
import com.haker.hakermovies.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class DetailFragment: Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()

    private var isFavoriteMovie: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        collectData()
    }

    fun initUI() {
        with(binding) {
            with(viewModel) {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

                getDetails()

                backButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    fun collectData() {
        with(binding) {
            with(viewModel) {
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    movieDetails.collectLatest { response ->
                        when(response) {
                            is Resource.Success -> {
                                with(response.data) {
                                    backDropIv.loadImage(backdropPath)

                                    titleTv.text = this.title
                                    overviewTv.text = this.overview
                                    voteAverageTv.text = this.voteAverage.format(1)
                                    yearTv.text = getReformatDate(this.releaseDate)

                                    StringBuilder().apply {
                                        append("Genres")
                                        append(" : ")
                                        append(genres.joinToString { it.name })
                                        textviewGenres.text = this.toString()
                                    }

                                    favoriteBtn.setOnClickListener {
                                        var favorite = Favorite(
                                            this.id,
                                            this.title,
                                            "",
                                            this.posterPath ?: "",
                                            this.voteAverage,
                                            MediaTypeEnum.MOVIE
                                        )

                                        if (isFavoriteMovie) {
                                            removeFavorite(favorite.id)
                                            isFavoriteMovie = false
                                            favoriteBtn.setImageResource(R.drawable.favorite)
                                        }
                                        else {
                                            addFavorite(favorite)
                                            isFavoriteMovie = true
                                            favoriteBtn.setImageResource(R.drawable.favorite_filled)
                                        }
                                    }
                                }
                            }

                            is Resource.Loading -> {

                            }

                            is Resource.Error -> {
                                requireActivity().showToast(
                                    "Error",
                                    response.throwable.localizedMessage ?: "Error",
                                    MotionToastStyle.ERROR
                                )
                            }
                        }
                    }
                }

                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    isFavorite.collectLatest { response ->
                        when (response) {
                            is Resource.Success -> {
                                if (response.data) {
                                    favoriteBtn.setImageResource(R.drawable.favorite_filled)
                                }
                                else {
                                    favoriteBtn.setImageResource(R.drawable.favorite)
                                }
                                isFavoriteMovie = response.data
                            }

                            is Resource.Loading -> {

                            }

                            is Resource.Error -> {
                                requireActivity().showToast(
                                    "Error",
                                    response.throwable.localizedMessage ?: "Error",
                                    MotionToastStyle.ERROR
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}