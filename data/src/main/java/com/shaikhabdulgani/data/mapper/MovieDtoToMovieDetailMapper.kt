package com.shaikhabdulgani.data.mapper

import com.shaikhabdulgani.data.source.remote.dto.GetMovieDetailDto
import com.shaikhabdulgani.domain.model.MovieDetail
import javax.inject.Inject

class MovieDtoToMovieDetailMapper @Inject constructor() : Mapper<GetMovieDetailDto, MovieDetail> {
    override fun map(from: GetMovieDetailDto): MovieDetail {
        return MovieDetail(
            id = from.id,
            name = from.title,
            description = from.overview,
            runtime = from.runtime.toString(),
            posterPath = from.posterPath ?: "",
            releaseDate = from.releaseDate,
            category = from.genres.map { it.name },
            backdropPath = from.backdropPath ?: ""
        )
    }
}