package com.shaikhabdulgani.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.shaikhabdulgani.data.mapper.MovieDtoToMovieDetailMapper
import com.shaikhabdulgani.data.mapper.MovieDtoToMovieMapper
import com.shaikhabdulgani.data.source.mediator.MoviePagingSource
import com.shaikhabdulgani.data.source.remote.MovieService
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieService,
    private val movieMapper: MovieDtoToMovieMapper,
    private val movieDetailMapper: MovieDtoToMovieDetailMapper
) : MovieRepository, BaseRepository() {
    override suspend fun getMovies(): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(api,movieMapper)
            }
        )
    }

    override suspend fun getMovieById(id: Int): Resource<MovieDetail, DataError> {
        return execute {
            val movieDto = api.getMovie(id)
            movieDetailMapper.map(movieDto)
        }
    }
}