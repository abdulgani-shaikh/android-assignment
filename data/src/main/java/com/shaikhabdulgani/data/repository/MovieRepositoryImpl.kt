package com.shaikhabdulgani.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shaikhabdulgani.data.mapper.toMovie
import com.shaikhabdulgani.data.mapper.toMovieDetail
import com.shaikhabdulgani.data.source.mediator.MoviePagingSource
import com.shaikhabdulgani.data.source.remote.MovieService
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieService
) : MovieRepository, BaseRepository() {
    override suspend fun getMovies(): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(api)
            }
        )
    }

    override suspend fun getMovieById(id: Int): Resource<MovieDetail> {
        return execute {
            api.getMovie(id).also { println(it) }.toMovieDetail()
        }
    }
}