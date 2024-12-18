package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.althaus.dev.cinemaNexus.ui.theme.DarkBackground
import com.althaus.dev.cinemaNexus.ui.theme.DarkSurface

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    startColor: Color = DarkBackground,
    endColor: Color = DarkSurface,
    content: @Composable () -> Unit
) {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(startColor, endColor)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        content()
    }
}