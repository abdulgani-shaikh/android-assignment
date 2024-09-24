package com.shaikhabdulgani.moviedb.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.shaikhabdulgani.moviedb.screens.components.AsyncImage
import com.shaikhabdulgani.moviedb.screens.components.RatingView
import com.shaikhabdulgani.moviedb.ui.theme.sizing
import com.shaikhabdulgani.moviedb.ui.theme.spacing


@Composable
fun MovieItemCard(movie: Movie, onItemClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.zero)
            .clickable { onItemClick.invoke(movie) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.spacing.extraSmall
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .height(MaterialTheme.sizing.posterHeight)
                    .fillMaxWidth(),
                imageUrl = movie.imageUrl,
                placeholder = movie.name
            )
            Text(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally),
                text = movie.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
    }
}

@Preview
@Composable
private fun MovieItemPrev() {
    MovieItemCard(movie = dummyMovie) { }
}

@Immutable
data class Movie(
    val id: String,
    val name: String,
    val imageUrl: String,
)

val dummyMovie = Movie("11", "Movie Name", "")