package com.shaikhabdulgani.moviedb.screens.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shaikhabdulgani.moviedb.screens.detail.DetailScreenData
import com.shaikhabdulgani.moviedb.ui.theme.LocalSpacing

@Composable
fun ThreeColDetail(
    modifier: Modifier = Modifier,
    movie: DetailScreenData
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(horizontal = spacing.default),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            spacing.defaultSmall,
            Alignment.CenterHorizontally
        ),
    ) {
        TextWithStartIcon(
            imageVector = Icons.Outlined.DateRange,
            text = movie.releaseDate
        )
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(20.dp)
                .background(
                    color = Color.DarkGray
                )
        )
        TextWithStartIcon(
            imageVector = Icons.Filled.PlayArrow,
            text = movie.runtime
        )
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(20.dp)
                .background(
                    color = Color.DarkGray
                )
        )
        TextWithStartIcon(
            imageVector = Icons.Filled.Info,
            text = if (movie.categories.isNotEmpty()) movie.categories[0] else ""
        )
    }
}