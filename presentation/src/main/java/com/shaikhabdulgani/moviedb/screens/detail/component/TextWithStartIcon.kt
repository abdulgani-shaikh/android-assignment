package com.shaikhabdulgani.moviedb.screens.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun TextWithStartIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    color: Color = Color.Black,
    drawablePadding: Dp = MaterialTheme.spacing.extraSmall,
) {
    Row(
        modifier = Modifier.then(modifier),
        horizontalArrangement = Arrangement.spacedBy(drawablePadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = text,
            tint = color
        )
        Text(
            text = text,
            color = color,
        )
    }
}