package com.akcay.samplecompose.di

import com.akcay.samplecompose.repository.MovieRepository
import com.akcay.samplecompose.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun provideMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}