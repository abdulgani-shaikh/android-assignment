package com.shaikhabdulgani.moviedb.screens.home

import com.shaikhabdulgani.domain.use_case.GetMovieListUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class HomeViewModelTest {

    private lateinit var viewmodel: HomeViewModel

    @Mock
    private lateinit var useCase: GetMovieListUseCase

    @Before
    fun setUp() {
       // useCase=GetMovieListUseCase()
        viewmodel = HomeViewModel(useCase)
    }


    @After
    fun tearDown() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun onEvent() {

    }
}