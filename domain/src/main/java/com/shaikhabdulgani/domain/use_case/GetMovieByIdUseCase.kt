package com.shaikhabdulgani.domain.use_case

import com.shaikhabdulgani.domain.base.BaseUseCase
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Int, Resource<MovieDetail>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(params: Int) = flow {
        emit(movieRepository.getMovieById(params))
    }
}