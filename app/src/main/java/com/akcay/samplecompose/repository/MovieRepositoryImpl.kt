package com.akcay.samplecompose.repository

import com.akcay.samplecompose.data.model.detailresponse.MovieDetailResponse
import com.akcay.samplecompose.data.model.listresponse.MovieResponse
import com.akcay.samplecompose.di.IoDispatcher
import com.akcay.samplecompose.network.MovieService
import com.akcay.samplecompose.util.NetworkResult
import com.akcay.samplecompose.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: MovieService
): MovieRepository {

    override suspend fun getAllPopularMovies(): NetworkResult<MovieResponse> {
        return safeApiCall(defaultDispatcher = dispatcher) {
            service.getAllPopularMovies()
        }
    }

    override suspend fun getMovieById(movieId: Long): NetworkResult<MovieDetailResponse> {
        return safeApiCall(defaultDispatcher = dispatcher) {
            service.getMovieById(movieId)
        }
    }

}