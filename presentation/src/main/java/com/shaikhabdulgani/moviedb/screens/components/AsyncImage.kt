package com.shaikhabdulgani.moviedb.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.shaikhabdulgani.moviedb.R

@Composable
fun AsyncImage(modifier: Modifier = Modifier, imageUrl: String, placeholder: String) {
    val context = LocalContext.current
    val imagePainter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data("${""}${imageUrl}")
            .size(Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isBlank()) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                painter = painterResource(id = R.drawable.img_placeholder),
                contentDescription = placeholder,
                contentScale = ContentScale.Inside,
            )
        } else {
            when (imagePainter.state) {
                AsyncImagePainter.State.Empty, is AsyncImagePainter.State.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    )
                }

                is AsyncImagePainter.State.Error -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Warning,
                        contentDescription = placeholder,
                        contentScale = ContentScale.Inside,
                    )
                }

                is AsyncImagePainter.State.Success -> {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = imagePainter,
                        contentDescription = placeholder,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}