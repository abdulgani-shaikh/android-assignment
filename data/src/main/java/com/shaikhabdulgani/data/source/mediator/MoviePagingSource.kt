package com.shaikhabdulgani.data.source.mediator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shaikhabdulgani.data.mapper.toMovie
import com.shaikhabdulgani.data.source.remote.MovieService
import com.shaikhabdulgani.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val movieService: MovieService,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = movieService.getMovies(
                pageNumber = currentPage
            )
            LoadResult.Page(
                data = movies.results.map { it.toMovie() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}