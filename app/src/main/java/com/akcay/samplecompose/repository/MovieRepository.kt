package com.akcay.samplecompose.repository

import com.akcay.samplecompose.data.model.detailresponse.MovieDetailResponse
import com.akcay.samplecompose.data.model.listresponse.MovieResponse
import com.akcay.samplecompose.util.NetworkResult

interface MovieRepository {

    suspend fun getAllPopularMovies(): NetworkResult<MovieResponse>

    suspend fun getMovieById(movieId: Long): NetworkResult<MovieDetailResponse>
}