package com.shaikhabdulgani.domain.use_case.details

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.google.common.truth.Truth.assertThat
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
import com.shaikhabdulgani.domain.use_case.GetMovieByIdUseCase
import com.shaikhabdulgani.domain.util.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class GetMovieByIdUseCaseTest {


    init {
        MockitoAnnotations.openMocks(this)
    }

    @Mock
    private lateinit var repo: MovieRepository
    private val useCase = GetMovieByIdUseCase(repo)

    @Before
    fun setUp() {
    }

    @Test
    fun `provide valid id and get success as result`() = runTest {
        val dummyMovieDetail = GetMovieByIdUseCaseFactory.getMovieDetailsResponse()
        val movieId = dummyMovieDetail.id
        val expected = Resource.Success<MovieDetail, DataError>(dummyMovieDetail)
        `when`(repo.getMovieById(movieId)).thenReturn(expected)
        turbineScope {
            useCase.invoke(movieId).test {
                val result = awaitItem()
                assertThat((result as Resource.Success).data.id).isEqualTo(movieId)
                assertThat(result).isEqualTo(expected)
                awaitComplete()
            }
        }
    }

    @Test
    fun `provide invalid id and get not found result`() = runTest {

        val expected = Resource.Error<MovieDetail, DataError>(DataError.Network.NOT_FOUND)
        val movieId = 1
        `when`(repo.getMovieById(movieId)).thenReturn(expected)
        turbineScope {
            useCase.invoke(movieId).test {
                val result = awaitItem()
                assertThat((result as Resource.Error).error).isEqualTo(expected.error)
                assertThat(result).isEqualTo(expected)
                awaitComplete()
            }
        }
    }
}