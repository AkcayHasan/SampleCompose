package com.akcay.samplecompose.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akcay.samplecompose.data.model.detailresponse.MovieDetailResponse
import com.akcay.samplecompose.repository.MovieRepository
import com.akcay.samplecompose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    private val _loadingState = MutableStateFlow(value = false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _movieById = MutableStateFlow<MovieDetailResponse?>(value = null)
    val movieById: StateFlow<MovieDetailResponse?> = _movieById

    fun getMovieDetailById(movieId: Long) {
        viewModelScope.launch {
            showLoading()
            try {
                when (val response = repo.getMovieById(movieId = movieId)) {
                    is NetworkResult.Success -> {
                        _movieById.value = response.data
                        Log.d("osman", "success: ${response.data}")
                    }

                    is NetworkResult.Error -> {
                        Log.d("osman", "error: ${response.message}")
                    }

                    is NetworkResult.Exception -> {
                        Log.d("osman", "getMovieById: ${response.e}")
                    }
                }
            } finally {
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        _loadingState.value = true
    }

    private fun hideLoading() {
        _loadingState.value = false
    }
}