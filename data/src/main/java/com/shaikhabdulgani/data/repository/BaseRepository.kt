package com.shaikhabdulgani.data.repository

import android.util.Log
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.error.RootError
import com.shaikhabdulgani.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> execute(block: suspend () -> T): Resource<T, RootError> {
        return try {
            val result = block()
            Resource.success(result)
        } catch (ex: HttpException) {
            Log.e("execute::HttpException", ex.message.toString())
            Resource.error(mapHttpCodeToNetworkError(ex.code()))
        } catch (ex: IOException) {
            Log.e("execute::IOException", ex.message.toString())
            Resource.error(DataError.Local.UNKNOWN)
        } catch (ex: Exception) {
            Log.e("execute::Exception", ex.message.toString())
            Resource.error(DataError.Unknown)
        }
    }

    private fun mapHttpCodeToNetworkError(httpCode: Int): DataError.Network {
        return when (httpCode) {
            401 -> DataError.Network.UNAUTHORIZED
            404 -> DataError.Network.NOT_FOUND
            408 -> DataError.Network.REQUEST_TIMEOUT
            429 -> DataError.Network.TOO_MANY_REQUESTS
            413 -> DataError.Network.PAYLOAD_TOO_LARGE
            500 -> DataError.Network.SERVER_ERROR
            400 -> DataError.Network.SERIALIZATION
            520 -> DataError.Network.UNKNOWN
            0 -> DataError.Network.NO_INTERNET
            else -> DataError.Network.UNKNOWN
        }
    }

}