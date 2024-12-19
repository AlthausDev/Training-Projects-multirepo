package com.althaus.dev.cinemaNexus.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// =======================================
// Tema Claro
// =======================================
val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    background = LightBackgroundMain,
    onBackground = LightTextPrimary,
    surface = LightBackgroundElevated,
    onSurface = LightTextSecondary,
    error = LightError,
    onError = LightOnError
)

// =======================================
// Tema Oscuro
// =======================================
val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,             // Fondo de botones principales y switches
    onPrimary = DarkOnPrimary,         // Texto o iconos en botones principales
    secondary = DarkSecondary,         // Fondo de chips secundarios o sliders
    onSecondary = DarkOnSecondary,     // Texto o iconos en chips secundarios
    background = DarkBackgroundMain,   // Fondo principal de la aplicación
    onBackground = DarkTextPrimary,    // Texto principal sobre el fondo
    surface = DarkBackgroundElevated,  // Fondo de tarjetas, AppBars o diálogos
    onSurface = DarkTextSecondary,     // Texto sobre tarjetas o AppBars
    error = DarkError,                 // Fondo de mensajes de error o bordes de campos inválidos
    onError = DarkOnError              // Texto dentro de mensajes de error
)


// =======================================
// Tema Centralizado
// =======================================
@Composable
fun CinemaNexusTheme(
    darkTheme: Boolean = true,
    //darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> DarkColorScheme
        // else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
