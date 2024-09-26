package com.shaikhabdulgani.data.mapper

import com.shaikhabdulgani.data.source.remote.dto.MovieDto
import com.shaikhabdulgani.domain.model.Movie
import javax.inject.Inject

class MovieDtoToMovieMapper @Inject constructor() : Mapper<MovieDto,Movie> {
    override fun map(from: MovieDto): Movie {
        return Movie(
            id = from.id,
            name = from.title,
            posterPath = from.posterPath ?: ""
        )
    }
}