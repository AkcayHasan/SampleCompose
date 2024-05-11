package com.akcay.samplecompose.network

import com.akcay.samplecompose.data.model.detailresponse.MovieDetailResponse
import com.akcay.samplecompose.data.model.listresponse.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular")
    suspend fun getAllPopularMovies(): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Long
    ): Response<MovieDetailResponse>
}