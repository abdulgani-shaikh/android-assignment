package com.shaikhabdulgani.moviedb.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaikhabdulgani.domain.model.MovieDetail
import com.shaikhabdulgani.moviedb.screens.detail.component.DetailTopHalf
import com.shaikhabdulgani.moviedb.screens.detail.component.MovieCategoryRow
import com.shaikhabdulgani.moviedb.screens.detail.component.ThreeColDetail
import com.shaikhabdulgani.moviedb.ui.theme.LocalSizing
import com.shaikhabdulgani.moviedb.ui.theme.LocalSpacing

@Composable
fun DetailScreen(
    controller: NavHostController,
    id: Int,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sizing = LocalSizing.current
    val spacing = LocalSpacing.current
    val movie = viewModel.movies.collectAsStateWithLifecycle()
    LaunchedEffect(context, id) {
        viewModel.getMovie(id)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacing.default)
    ) {
        DetailTopHalf(
            modifier = Modifier
                .fillMaxWidth()
                .height(sizing.backDropHeight),
            movie = movie.value,
            onBackClick = {
                controller.navigateUp()
            }
        )

        ThreeColDetail(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            movie = movie.value
        )

        Text(
            text = movie.value.description,
            modifier = Modifier.padding(horizontal = spacing.default)
        )

        MovieCategoryRow(movie.value)
    }
}

val dummyDetailData = MovieDetail(
    id = 0,
    name = "",
    posterPath = "",
    backdropPath = "",
    releaseDate = "",
    runtime = "",
    category = emptyList(),
    description = ""
)

@Preview
@Composable
private fun DetailScreenPrev() {
    DetailScreen(controller = rememberNavController(), id = 0)
}