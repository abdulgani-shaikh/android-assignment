package com.shaikhabdulgani.moviedb.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shaikhabdulgani.moviedb.ui.theme.spacing

@Composable
fun SectionWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    containsRow: Boolean = false,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.default),
        modifier = modifier
    ) {
        Text(
            modifier = if (containsRow) Modifier.padding(start = MaterialTheme.spacing.default) else Modifier,
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        content()
    }
}
