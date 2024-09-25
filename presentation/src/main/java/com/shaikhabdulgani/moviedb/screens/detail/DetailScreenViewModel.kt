package com.shaikhabdulgani.moviedb.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.use_case.GetMovieByIdUseCase
import com.shaikhabdulgani.domain.util.Resource
import com.shaikhabdulgani.moviedb.util.ErrorsMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getMovieById: GetMovieByIdUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<MovieDetail> = MutableStateFlow(dummyDetailData)
    val movies: StateFlow<MovieDetail> get() = _movies

    private val _error: MutableSharedFlow<ErrorsMessage> = MutableSharedFlow()
    val error: SharedFlow<ErrorsMessage> get() = _error

    fun getMovie(id: Int) = viewModelScope.launch {
        getMovieById(id)
            .catch {

            }
            .collect { resource ->
                when (resource) {
                    is Resource.Error -> {

                    }

                    is Resource.Success -> {
                        Log.d("asda",resource.data.toString())
                        resource.data?.let {
                            _movies.emit(it)
                        }
                    }
                }
            }
    }

}