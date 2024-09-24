@file:Suppress("UNUSED_PARAMETER")

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaikhabdulgani.moviedb.screens.detail.component.DetailTopHalf
import com.shaikhabdulgani.moviedb.screens.detail.component.MovieCategoryRow
import com.shaikhabdulgani.moviedb.screens.detail.component.ThreeColDetail
import com.shaikhabdulgani.moviedb.ui.theme.LocalSizing
import com.shaikhabdulgani.moviedb.ui.theme.LocalSpacing

@Composable
fun DetailScreen(controller: NavHostController, id: String) {
    val sizing = LocalSizing.current
    val spacing = LocalSpacing.current
    val movie by remember {
        mutableStateOf(dummyDetailData)
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
            movie = movie,
            onBackClick = {
                controller.navigateUp()
            }
        )

        ThreeColDetail(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            movie = movie
        )

        Text(
            text = movie.description,
            modifier = Modifier.padding(horizontal = spacing.default)
        )

        MovieCategoryRow(movie)
    }
}

data class DetailScreenData(
    val movieName: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val runtime: String,
    val categories: List<String>,
    val description: String,
)

val dummyDetailData = DetailScreenData(
    movieName = "Tere Naam",
    posterUrl = "",
    backdropUrl = "",
    releaseDate = "2003",
    runtime = "132 mins",
    categories = listOf("Drama", "Romance", "Action", "Tragedy"),
    description = "Radhe, a rowdy boy, falls in love with Nirjara, a first year college student. After initial hatred, when Nirjara reciprocates his love, a brutal attack renders him mentally unstable."
)

@Preview
@Composable
private fun DetailScreenPrev() {
    DetailScreen(controller = rememberNavController(), id = "")
}