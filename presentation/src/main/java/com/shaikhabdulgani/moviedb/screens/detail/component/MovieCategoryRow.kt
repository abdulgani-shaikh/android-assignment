package com.shaikhabdulgani.moviedb.screens.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.shaikhabdulgani.moviedb.R
import com.shaikhabdulgani.moviedb.screens.detail.DetailScreenData
import com.shaikhabdulgani.moviedb.ui.theme.LocalSpacing
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun MovieCategoryRow(
    movie: DetailScreenData
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .padding(horizontal = spacing.default),
        verticalArrangement = Arrangement.spacedBy(spacing.defaultSmall)
    ) {
        Text(text = stringResource(R.string.category_s))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.defaultSmall)
        ) {
            items(movie.categories) {
                CategoryChip(text = it)
            }
        }
    }
}