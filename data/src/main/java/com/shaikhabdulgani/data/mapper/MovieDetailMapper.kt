package com.shaikhabdulgani.data.mapper

import com.shaikhabdulgani.data.source.remote.dto.GetMovieDetailDto
import com.shaikhabdulgani.domain.model.MovieDetail

fun GetMovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        name = title,
        description = overview,
        runtime = runtime.toString(),
        posterPath = posterPath ?: "",
        releaseDate = releaseDate,
        category = genres.map { it.name },
        backdropPath = backdropPath ?: ""
    )
}

interface Mapper<FROM, TO> {
    fun toDomain(from: FROM): TO
}