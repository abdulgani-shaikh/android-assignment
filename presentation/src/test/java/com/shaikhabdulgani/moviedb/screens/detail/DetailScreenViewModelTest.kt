package com.shaikhabdulgani.moviedb.screens.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.use_case.GetMovieByIdUseCase
import com.shaikhabdulgani.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailScreenViewModelTest {

    init {
        MockitoAnnotations.openMocks(this)
    }

    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: MovieRepository
    private val useCase = GetMovieByIdUseCase(repo)

    private val viewModel = DetailScreenViewModel(useCase)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // TODO: This test is currently failing with app.cash.turbine.TurbineAssertionError: No value produced in 3s
    @Test
    fun onEvent() = testScope.runTest {
        val movieId = 1
        val dummyMovieDetail = MovieDetail(
            id = movieId,
            name = "Dummy",
            posterPath = "/58QT4cPJ2u2TqWZkterDq9q4yxQ.jpg",
            backdropPath = "/58QT4cPJ2u2TqWZkterDq9q4yxQ.jpg",
            releaseDate = "2021",
            runtime = "123",
            category = listOf("Horror", "Comedy"),
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"
        )

        val expected = Resource.Success<MovieDetail, DataError>(dummyMovieDetail)

        Mockito.`when`(repo.getMovieById(movieId)).thenReturn(expected)

        viewModel.onEvent(DetailScreenEvent.GetDetail(movieId))

        viewModel.movies.test {
            skipItems(1)
            val result = this.awaitItem()
            assertThat(result.id).isEqualTo(expected.data.id)
            awaitComplete()
        }
    }


}