package com.shaikhabdulgani.data.repository

import android.util.Log
import com.shaikhabdulgani.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    suspend fun <T> execute(block: suspend () -> T): Resource<T> {
        try {
            val result = block()
            return Resource.Success(result)
        } catch (ex: HttpException) {
            Log.e("BaseRepository",ex.message.toString())
            return Resource.Error(ex)
        } catch (ex: IOException) {
            Log.e("BaseRepository",ex.message.toString())
            return Resource.Error(ex)
        } catch (ex: Exception) {
            Log.e("BaseRepository",ex.message.toString())
            return Resource.Error(ex)
        }
    }
}