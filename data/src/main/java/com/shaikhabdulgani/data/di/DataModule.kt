package com.shaikhabdulgani.data.di

import com.shaikhabdulgani.data.BuildConfig
import com.shaikhabdulgani.data.repository.MovieRepositoryImpl
import com.shaikhabdulgani.data.source.remote.MovieService
import com.shaikhabdulgani.data.util.AuthInterceptor
import com.shaikhabdulgani.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .addNetworkInterceptor(AuthInterceptor(BuildConfig.API_KEY))
                    .build(),
            )
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(
            MovieService::class.java,
        )
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository =
        MovieRepositoryImpl(movieService)

}

