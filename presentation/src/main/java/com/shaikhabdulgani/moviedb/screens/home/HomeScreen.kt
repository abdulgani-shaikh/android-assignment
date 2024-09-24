package com.shaikhabdulgani.moviedb.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shaikhabdulgani.moviedb.R
import com.shaikhabdulgani.moviedb.navigation.Screens
import com.shaikhabdulgani.moviedb.screens.components.SectionWithTitle
import com.shaikhabdulgani.moviedb.screens.home.components.MediaRow
import com.shaikhabdulgani.moviedb.screens.home.components.Movie
import com.shaikhabdulgani.moviedb.screens.home.components.MovieItemCard
import com.shaikhabdulgani.moviedb.screens.home.components.dummyMovie
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun HomeScreen(controller: NavHostController) {
    Scaffold(
        topBar = {
            HomeTopBar()
        }
    ) { paddingValues ->
//        Column(
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            MediaRow(title = "Latest", movies = emptyList()) { movie ->
//                controller.navigate(Screens.Detail(movie.id))
//            }
//            MediaRow(title = "Upcoming", movies = emptyList()) { movie ->
//                controller.navigate(Screens.Detail(movie.id))
//            }
//        }
        HomeMovieList(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            list = dummyMoviesList,
            onLastReached = {},
            onItemClick = { movie ->
                controller.navigate(Screens.Detail(movie.id))
            }
        )
    }
}


@Composable
private fun HomeMovieList(
    modifier: Modifier = Modifier,
    list: List<Movie>,
    onLastReached: () -> Unit,
    onItemClick: (Movie) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(MaterialTheme.spacing.default)
    ) {
        itemsIndexed(list) { index, movie ->
            if (index == list.size - 1) {
                onLastReached.invoke()
            }
            MovieItemCard(movie, onItemClick)
        }
    }
}
private val dummyMoviesList = listOf(
    dummyMovie,
    dummyMovie,
    dummyMovie,
    dummyMovie,
    dummyMovie,
    dummyMovie,
)

@Composable
fun HomeTopBar() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.zero)
            .background(Color.White)
            .padding(MaterialTheme.spacing.default),
        text = stringResource(id = R.string.app_name),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(controller = rememberNavController())
}