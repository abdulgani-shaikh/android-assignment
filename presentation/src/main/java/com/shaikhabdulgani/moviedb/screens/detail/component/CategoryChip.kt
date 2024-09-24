package com.shaikhabdulgani.moviedb.screens.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun CategoryChip(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.White,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color = Color.DarkGray)
            .padding(
                horizontal = MaterialTheme.spacing.defaultSmall,
                vertical = MaterialTheme.spacing.small
            )
    )
}