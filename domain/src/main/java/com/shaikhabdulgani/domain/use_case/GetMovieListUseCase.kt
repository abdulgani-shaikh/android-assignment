package com.shaikhabdulgani.domain.use_case

import com.shaikhabdulgani.domain.base.BaseUseCase
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Unit, Resource<List<Movie>>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(params: Unit) = flow {
        emit(movieRepository.getMovies())
    }.catch {
        emit(Resource.Error(it))
    }
}