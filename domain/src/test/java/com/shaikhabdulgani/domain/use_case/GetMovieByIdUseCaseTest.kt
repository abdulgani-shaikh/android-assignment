package com.shaikhabdulgani.domain.use_case

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.google.common.truth.Truth.assertThat
import com.shaikhabdulgani.domain.error.DataError
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.domain.repository.MovieRepository
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