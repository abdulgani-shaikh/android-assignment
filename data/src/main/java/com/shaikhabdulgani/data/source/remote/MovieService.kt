package com.shaikhabdulgani.data.source.remote

import com.shaikhabdulgani.data.source.remote.dto.GetMovieDetailDto
import com.shaikhabdulgani.data.source.remote.dto.GetMovieListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("/3/movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Int
    ): GetMovieDetailDto

    @GET("/3/movie/popular")
    suspend fun getMovies(): GetMovieListResponseModel

}