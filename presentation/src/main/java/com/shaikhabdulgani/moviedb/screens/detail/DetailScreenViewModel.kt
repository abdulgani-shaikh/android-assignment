package com.shaikhabdulgani.moviedb.screens.detail

import androidx.lifecycle.ViewModel
import com.shaikhabdulgani.moviedb.screens.home.components.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailScreenViewModel : ViewModel() {

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

}