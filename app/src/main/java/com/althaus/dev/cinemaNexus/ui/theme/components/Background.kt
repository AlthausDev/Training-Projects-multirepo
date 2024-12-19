package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.althaus.dev.cinemaNexus.ui.theme.DarkBackgroundElevated
import com.althaus.dev.cinemaNexus.ui.theme.DarkBackgroundMain
import com.althaus.dev.cinemaNexus.ui.theme.DarkBackgroundMid

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    colors: List<Color> = listOf(DarkBackgroundMain, DarkBackgroundMid, DarkBackgroundElevated),

    gradientDirection: GradientDirection = GradientDirection.Vertical, // Dirección configurable
    content: @Composable () -> Unit
) {
    val gradientBrush = when (gradientDirection) {
        GradientDirection.Vertical -> Brush.verticalGradient(colors = colors)
        GradientDirection.Horizontal -> Brush.horizontalGradient(
            colors = colors
        )

        GradientDirection.Diagonal -> Brush.linearGradient(
            colors = colors,
            start = androidx.compose.ui.geometry.Offset(0f, 0f),
            end = androidx.compose.ui.geometry.Offset(
                Float.POSITIVE_INFINITY,
                Float.POSITIVE_INFINITY
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        content()
    }
}

/** Dirección configurable para el degradado. */
enum class GradientDirection {
    Vertical,
    Horizontal,
    Diagonal
}
