package com.shaikhabdulgani.domain.repository

import androidx.paging.Pager
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.util.Resource

interface MovieRepository {
    suspend fun getMovies(): Pager<Int, Movie>
    suspend fun getMovieById(id: Int): Resource<MovieDetail, DataError>
}