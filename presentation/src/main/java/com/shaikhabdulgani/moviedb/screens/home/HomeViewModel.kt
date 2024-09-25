package com.shaikhabdulgani.moviedb.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.domain.use_case.GetMovieListUseCase
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
class HomeViewModel @Inject constructor(
    private val getMovieList: GetMovieListUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<List<Movie>> =
        MutableStateFlow(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _error: MutableSharedFlow<ErrorsMessage> = MutableSharedFlow()
    val error: SharedFlow<ErrorsMessage> get() = _error

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch {
        getMovieList(Unit)
            .catch {

            }.collect { resource ->
                when (resource) {
                    is Resource.Error -> {

                    }

                    is Resource.Success -> {
                        resource.data?.let {
                            _movies.emit(it)
                        }
                    }
                }
            }
    }
}