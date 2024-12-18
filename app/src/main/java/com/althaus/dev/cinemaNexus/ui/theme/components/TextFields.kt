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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun SharedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fielWidth: Float = 0.8f,
    textColor: Color = Black,
    backgroundColor: Color = White,
    placeholderColor: Color = Black,
    borderColor: Color = Black,
    label: String? = null,
    placeholder: String = ""
) {


    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(fielWidth)
            .padding(vertical = 8.dp)
            .background(backgroundColor)
            .border(2.dp, borderColor, CircleShape),
        singleLine = true,
        textStyle = TextStyle(textColor),

        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor
                    )
                }
                innerTextField()
            }
        }
    )
}