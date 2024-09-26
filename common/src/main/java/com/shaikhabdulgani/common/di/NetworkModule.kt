package com.shaikhabdulgani.common.di

import com.shaikhabdulgani.data.repository.MovieRepositoryImpl
import com.shaikhabdulgani.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(movieService: MovieRepositoryImpl): MovieRepository

}
