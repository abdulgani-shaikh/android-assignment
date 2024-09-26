package com.shaikhabdulgani.moviedb.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.use_case.GetMovieByIdUseCase
import com.shaikhabdulgani.domain.util.Resource
import com.shaikhabdulgani.moviedb.util.UiText
import com.shaikhabdulgani.moviedb.util.asErrorUiText
import com.shaikhabdulgani.moviedb.util.emptyMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getMovieById: GetMovieByIdUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<MovieDetail> = MutableStateFlow(emptyMovieDetail)
    val movies: StateFlow<MovieDetail> get() = _movies

    private val _userEvent: MutableSharedFlow<UserEvent> = MutableSharedFlow()
    val userEvent: SharedFlow<UserEvent> get() = _userEvent

    fun onEvent(event: DetailScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is DetailScreenEvent.GetDetail -> {
                    getMovie(event.id)
                }
            }
        }
    }

    private fun getMovie(id: Int) = viewModelScope.launch {
        getMovieById(id)
            .collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _userEvent.emit(UserEvent.Error(resource.asErrorUiText()))
                    }

                    is Resource.Success -> {
                        _movies.emit(resource.data)
                    }
                }
            }
    }

}

sealed class DetailScreenEvent {
    data class GetDetail(val id: Int) : DetailScreenEvent()
}

sealed interface UserEvent {
    data class Error(val error: UiText): UserEvent
}