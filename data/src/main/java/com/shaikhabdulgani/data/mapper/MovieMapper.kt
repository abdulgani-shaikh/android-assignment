package com.shaikhabdulgani.data.mapper

import com.shaikhabdulgani.data.source.remote.dto.MovieDto
import com.shaikhabdulgani.domain.model.Movie

fun MovieDto.toMovie() : Movie{
    return Movie(
        id = id,
        name = title,
        posterPath = posterPath ?: ""
    )
}