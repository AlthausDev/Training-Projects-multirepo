package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.ui.theme.CinemaNexusTheme

@Composable
fun SharedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fieldWidth: Float = 0.9f,
    placeholder: String = "",
    enabled: Boolean = true,
    colors: TextFieldColors = getTextFieldColors(enabled),
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Box(
        modifier = modifier
            .fillMaxWidth(fieldWidth)
            .height(48.dp)
            .background(color = colors.backgroundColor, shape = CircleShape)
            .border(width = 2.dp, color = colors.borderColor, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        // Placeholder
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                color = colors.placeholderColor,
                style = textStyle
            )
        }
        // Input Field
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            enabled = enabled,
            textStyle = textStyle.copy(color = colors.textColor),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ----------------------------
// Data Class for Colors
// ----------------------------
data class TextFieldColors(
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color,
    val placeholderColor: Color
)

// ----------------------------
// Helper Function for Colors
// ----------------------------
@Composable
private fun getTextFieldColors(enabled: Boolean): TextFieldColors {
    return TextFieldColors(
        backgroundColor = if (enabled) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.12f
        ),
        borderColor = if (enabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.3f
        ),
        textColor = MaterialTheme.colorScheme.onSurface,
        placeholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    )
}

// ----------------------------
// Previews
// ----------------------------
@Preview
@Composable
fun SharedTextFieldPreviewEnabled() {
    CinemaNexusTheme(darkTheme = true) {
        SharedTextField(
            value = "",
            onValueChange = {},
            placeholder = "Email",
            enabled = true
        )
    }
}

@Preview
@Composable
fun SharedTextFieldPreviewDisabled() {
    CinemaNexusTheme(darkTheme = true) {
        SharedTextField(
            value = "",
            onValueChange = {},
            placeholder = "Email",
            enabled = false
        )
    }
}

@Preview
@Composable
fun SharedTextFieldPreviewLightEnabled() {
    CinemaNexusTheme(darkTheme = false) {
        SharedTextField(
            value = "",
            onValueChange = {},
            placeholder = "Email",
            enabled = true
        )
    }
}
