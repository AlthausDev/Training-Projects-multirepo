package com.althaus.dev.cinemaNexus.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.ui.theme.CinemaNexusTheme

/**
 * Tipo de mensaje a mostrar.
 */
enum class MessageType {
    ERROR, SUCCESS, INFO
}

/**
 * Componente genérico para mostrar mensajes de error, éxito o información.
 *
 * @param message Mensaje a mostrar.
 * @param type Tipo del mensaje (ERROR, SUCCESS, INFO).
 * @param modifier Modificador opcional para el contenedor del mensaje.
 */
@Composable
fun MessageDisplay(
    message: String,
    type: MessageType,
    modifier: Modifier = Modifier
) {
    val backgroundColor: Color
    val textColor: Color

    when (type) {
        MessageType.ERROR -> {
            backgroundColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
            textColor = MaterialTheme.colorScheme.error
        }

        MessageType.SUCCESS -> {
            backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            textColor = MaterialTheme.colorScheme.primary
        }

        MessageType.INFO -> {
            backgroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
            textColor = MaterialTheme.colorScheme.secondary
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

// ----------------------------
// Previews
// ----------------------------
@Preview
@Composable
fun MessageDisplayErrorPreview() {
    CinemaNexusTheme(darkTheme = true) {
        MessageDisplay(message = "Ocurrió un error inesperado", type = MessageType.ERROR)
    }
}

@Preview
@Composable
fun MessageDisplaySuccessPreview() {
    CinemaNexusTheme(darkTheme = false) {
        MessageDisplay(message = "Registro exitoso", type = MessageType.SUCCESS)
    }
}

@Preview
@Composable
fun MessageDisplayInfoPreview() {
    CinemaNexusTheme(darkTheme = true) {
        MessageDisplay(message = "Cargando información...", type = MessageType.INFO)
    }
}
