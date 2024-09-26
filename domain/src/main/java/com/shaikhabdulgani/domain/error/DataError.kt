package com.shaikhabdulgani.domain.error

sealed interface DataError : RootError {
    enum class Network : DataError {
        UNAUTHORIZED,
        NOT_FOUND,
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
    }

    enum class Local : DataError {
        UNKNOWN
    }

    data object  Unknown : DataError
}