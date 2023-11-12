package com.haker.hakermovies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.haker.hakermovies.MediaTypeEnum
import com.haker.hakermovies.R
import com.haker.hakermovies.databinding.FragmentHomeBinding
import com.haker.hakermovies.showToast
import com.haker.hakermovies.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment"

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val adapterPopularMovies: MovieAdapter by lazy { MovieAdapter(::onClickMovieItem) }
    private val adapterUpcomingMovies: MovieAdapter by lazy { MovieAdapter(::onClickMovieItem) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectData()
    }

    private fun collectData() {
        with(viewModel) {
            with(binding) {

                //popular_movies
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    popularMovies.collectLatest { response ->
                        recyclerViewPopularMovies.adapter = adapterPopularMovies
                        adapterPopularMovies.submitData(lifecycle, response)

                        adapterPopularMovies.loadStateFlow.collectLatest { loadStates ->
                            when (loadStates.refresh) {
                                is LoadState.Loading -> {
                                    Log.d(TAG, "Loading")
                                    recyclerViewPopularMovies.visibility = View.GONE
                                }
                                is LoadState.NotLoading -> {
                                    Log.d(TAG, "NotLoading")
                                    recyclerViewPopularMovies.visibility = View.VISIBLE
                                }
                                is LoadState.Error -> {
                                    requireActivity().showToast(
                                        "Error",
                                        (loadStates.refresh as LoadState.Error).error.localizedMessage
                                            ?: "Error",
                                        MotionToastStyle.ERROR
                                    )
                                }

                            }
                        }
                    }
                }

                //upcoming_movies
                viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                    upcomingMovies.collectLatest { response ->
                        recyclerViewUpcomingMovies.adapter = adapterUpcomingMovies
                        adapterUpcomingMovies.submitData(lifecycle, response)

                        adapterUpcomingMovies.loadStateFlow.collectLatest { loadStates ->
                            when (loadStates.refresh) {
                                is LoadState.Loading -> {
                                    Log.d(TAG, "Loading")
                                    recyclerViewUpcomingMovies.visibility = View.GONE
                                }
                                is LoadState.NotLoading -> {
                                    Log.d(TAG, "NotLoading")
                                    recyclerViewUpcomingMovies.visibility = View.VISIBLE
                                }
                                is LoadState.Error -> {
                                    requireActivity().showToast(
                                        "Error",
                                        (loadStates.refresh as LoadState.Error).error.localizedMessage
                                            ?: "Error",
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

    private fun onClickMovieItem(movie: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie, MediaTypeEnum.MOVIE)
        findNavController().navigate(action)
    }
}