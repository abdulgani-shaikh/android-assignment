package com.shaikhabdulgani.domain.util

sealed class Resource<out T>(val data: T?, val exception: Throwable?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(exception: Throwable?, data: T? = null) : Resource<T>(data, exception)
}