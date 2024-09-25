package com.shaikhabdulgani.domain.repository

import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.util.Resource

interface MovieRepository {
    suspend fun getMovies(): Resource<List<Movie>>
    suspend fun getMovieById(id: Int): Resource<MovieDetail>
}