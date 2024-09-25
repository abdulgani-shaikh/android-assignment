package com.shaikhabdulgani.moviedb.util

import androidx.annotation.StringRes
import com.shaikhabdulgani.moviedb.R

enum class ErrorsMessage(@StringRes val message: Int) {
    NO_INTERNET(R.string.no_data_available),
    UNAUTHORIZED(R.string.no_data_available),
    BAD_PARAMS(R.string.no_data_available),
    ;
}