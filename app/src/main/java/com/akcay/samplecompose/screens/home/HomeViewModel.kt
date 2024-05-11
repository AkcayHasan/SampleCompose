package com.akcay.samplecompose.screens.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akcay.samplecompose.data.model.listresponse.MovieResponse
import com.akcay.samplecompose.repository.MovieRepository
import com.akcay.samplecompose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MovieRepository
): ViewModel() {

    private val _loadingState = MutableStateFlow(value = false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _popularMovieList = MutableStateFlow<MovieResponse?>(value = null)
    val popularMovieList: StateFlow<MovieResponse?> = _popularMovieList

    init {
        getAllPopularMovies()
    }

    private fun getAllPopularMovies() {
        viewModelScope.launch {
            showLoading()
            when(val response = repo.getAllPopularMovies()) {
                is NetworkResult.Success -> {
                    hideLoading()
                    _popularMovieList.value = response.data
                    Log.d("osman", "success: ${response.data}")
                }
                is NetworkResult.Error -> {
                    hideLoading()
                    Log.d("osman", "error: ${response.message}")
                }
                is NetworkResult.Exception -> {
                    hideLoading()
                    Log.d("osman", "getAllPopularMovies: ${response.e}")
                }
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