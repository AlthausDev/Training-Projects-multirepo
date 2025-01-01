package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    gradientDirection: GradientDirection = GradientDirection.Vertical, // Dirección configurable
    content: @Composable () -> Unit
) {
    // Obtener el color de fondo según el tema
    val backgroundColor = MaterialTheme.colorScheme.background // Cambia de acuerdo con el tema

    // Crear el degradado dinámicamente en función del color de fondo
    val gradientBrush = when (gradientDirection) {
        GradientDirection.Vertical -> Brush.verticalGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor.copy(alpha = 0.7f)
            )
        )

        GradientDirection.Horizontal -> Brush.horizontalGradient(
            colors = listOf(
                backgroundColor,
                backgroundColor.copy(alpha = 0.7f)
            )
        )

        GradientDirection.Diagonal -> Brush.linearGradient(
            colors = listOf(backgroundColor, backgroundColor.copy(alpha = 0.7f)),
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
