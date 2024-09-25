package com.shaikhabdulgani.moviedb.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shaikhabdulgani.domain.model.Movie
import com.shaikhabdulgani.moviedb.BuildConfig
import com.shaikhabdulgani.moviedb.R
import com.shaikhabdulgani.moviedb.screens.components.SectionWithTitle
import com.shaikhabdulgani.moviedb.ui.theme.sizing
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun MediaRow(
    modifier: Modifier = Modifier,
    title: String,
    movies: List<Movie>,
    isLoading: Boolean = true,
    onLastReached: () -> Unit = {},
    onClick: (Movie) -> Unit
) {
    SectionWithTitle(
        modifier = modifier,
        title = title,
        containsRow = true
    ) {
        if (movies.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(items = movies, key = { i, movie -> "${movie.id}$i" }) { i, item ->
                    if (i == movies.size - 1) {
                        onLastReached()
                    }
                    MovieItemCard(
                        modifier = when {
                            i == movies.size - 1 && !isLoading -> Modifier.padding(end = 20.dp)
                            i == 0 -> Modifier.padding(start = 20.dp)
                            else -> Modifier
                        },
                        item = item,
                        onClick = {
                            onClick(it)
                        }
                    )
                }
                val loadingItemCount = 3
                if (isLoading) {
                    items(loadingItemCount) {
                        LoadingItem(
                            modifier = when (it) {
                                loadingItemCount - 1 -> Modifier.padding(end = 20.dp)
                                0 -> Modifier.padding(start = 20.dp)
                                else -> Modifier
                            },
                        )
                    }
                }
            }
        } else {
            NoDataAvailable(
                modifier.padding(horizontal = MaterialTheme.spacing.default)
            )
        }
    }
}

@Composable
private fun MovieItemCard(
    modifier: Modifier = Modifier,
    item: Movie,
    onClick: (Movie) -> Unit,
) {
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data("${BuildConfig.BASE_IMAGE_URL}${item.posterPath}")
            .size(Size.ORIGINAL)
            .build()
    )
    Box(
        modifier
            .size(height = 180.dp, width = 120.dp)
            .clip(RoundedCornerShape(MaterialTheme.sizing.defaultCornerSize))
            .background(Color.LightGray)
            .clickable {
                onClick.invoke(item)
            },
        contentAlignment = Alignment.Center
    ) {
        if (item.posterPath.isBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = item.name,
                textAlign = TextAlign.Center
            )
        } else {
            when (image.state) {
                AsyncImagePainter.State.Empty -> {

                }

                is AsyncImagePainter.State.Error -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                    )
                }

                is AsyncImagePainter.State.Loading -> {
                    LoadingItem()
                }

                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = image,
                        contentDescription = item.name,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingItem(
    modifier: Modifier = Modifier
) {

    Box(
        modifier
            .size(height = 180.dp, width = 120.dp)
    ){
        CircularProgressIndicator()
    }
}

@Composable
private fun NoDataAvailable(
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .height(180.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.default,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier.fillMaxHeight(0.7f),
            painter = painterResource(id = R.drawable.img_no_data),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.no_data_available),
            textAlign = TextAlign.Center
        )
    }
}