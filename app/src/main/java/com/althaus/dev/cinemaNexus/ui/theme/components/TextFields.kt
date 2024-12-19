package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SharedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fieldWidth: Float = 0.9f,
    backgroundColor: Color = Color.LightGray,
    placeholderColor: Color = Color.DarkGray,
    borderColor: Color = Color.Black,
    placeholder: String = "",
    textStyle: TextStyle = TextStyle.Default
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(fieldWidth)
            .background(backgroundColor, CircleShape)
            .border(2.dp, borderColor, CircleShape)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        singleLine = true,
        textStyle = textStyle,

        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        style = textStyle
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun SharedTextFieldPreview() {
    SharedTextField(
        value = "",
        onValueChange = {},
        placeholder = "Email"
    )
}
