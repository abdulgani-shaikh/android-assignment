package com.shaikhabdulgani.moviedb.screens.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shaikhabdulgani.moviedb.R
import com.shaikhabdulgani.moviedb.screens.components.AsyncImage
import com.shaikhabdulgani.moviedb.screens.detail.DetailScreenData
import com.shaikhabdulgani.moviedb.ui.theme.LocalSizing
import com.shaikhabdulgani.moviedb.ui.theme.LocalSpacing
import com.shaikhabdulgani.moviedb.util.noRippleClickable

@Composable
fun DetailTopHalf(
    modifier: Modifier = Modifier,
    movie: DetailScreenData,
    onBackClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    val sizing = LocalSizing.current
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize(),
            imageUrl = movie.backdropUrl,
            placeholder = movie.backdropUrl
        )
        Row(
            modifier = Modifier
                .padding(top = spacing.default)
                .padding(spacing.medium)
                .noRippleClickable { onBackClick.invoke() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.default)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = stringResource(R.string.back),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(spacing.zero)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White)
                    )
                )
                .padding(horizontal = spacing.default)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.default)
        ) {
            Card(
                modifier = Modifier
                    .height(sizing.detailPosterHeight)
                    .width(sizing.detailPosterWidth),
                shape = RoundedCornerShape(sizing.defaultCornerSize),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = spacing.defaultSmall
                )
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = movie.posterUrl,
                    placeholder = movie.posterUrl
                )
            }
            Text(
                text = movie.movieName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}