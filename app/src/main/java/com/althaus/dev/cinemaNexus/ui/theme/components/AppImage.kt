package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppImage(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(
        modifier = modifier.size(size)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(size),
            contentScale = contentScale
        )
    }
}
