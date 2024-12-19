package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.ui.theme.CinemaNexusTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = getButtonColors(enabled)
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(48.dp)
            .border(width = 2.dp, colors.borderColor, shape = CircleShape)
            .background(color = colors.backgroundColor, shape = CircleShape)
            .clickable(enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colors.contentColor,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}

// ----------------------------
// Data Class for Colors
// ----------------------------
data class ButtonColors(
    val borderColor: Color,
    val backgroundColor: Color,
    val contentColor: Color
)

// ----------------------------
// Helper Function for Colors
// ----------------------------
@Composable
private fun getButtonColors(enabled: Boolean): ButtonColors {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
    }
    val backgroundColor = if (enabled) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    }
    val contentColor = if (enabled) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }
    return ButtonColors(borderColor, backgroundColor, contentColor)
}

// ----------------------------
// Previews
// ----------------------------
@Preview
@Composable
fun PrimaryButtonPreviewEnabled() {
    CinemaNexusTheme(darkTheme = true) {
        PrimaryButton(text = "Primary Button", onClick = {}, enabled = true)
    }
}

@Preview
@Composable
fun PrimaryButtonPreviewDisabled() {
    CinemaNexusTheme(darkTheme = true) {
        PrimaryButton(text = "Primary Button", onClick = {}, enabled = false)
    }
}

@Preview
@Composable
fun PrimaryButtonPreviewLightEnabled() {
    CinemaNexusTheme(darkTheme = false) {
        PrimaryButton(text = "Primary Button", onClick = {}, enabled = true)
    }
}
