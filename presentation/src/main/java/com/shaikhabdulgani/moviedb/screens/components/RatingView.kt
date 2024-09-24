package com.shaikhabdulgani.moviedb.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shaikhabdulgani.moviedb.ui.theme.spacing

// throws exception if current rating is > max rating
@Composable
fun RatingView(
    modifier: Modifier = Modifier,
    currentRating: Int,
    maxRating: Int,
    activeColor: Color,
    inactiveColor: Color,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(MaterialTheme.spacing.small)
) {
    // Ensure currentRating is within a valid range
    require(currentRating in 0..maxRating) { "currentRating must be between 0 and maxRating." }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        repeat(currentRating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = activeColor
            )
        }
        repeat(maxRating - currentRating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = inactiveColor
            )
        }
    }
}