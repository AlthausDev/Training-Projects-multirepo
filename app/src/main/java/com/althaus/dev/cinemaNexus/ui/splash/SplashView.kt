package com.althaus.dev.cinemaNexus.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.AppImage
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.PrimaryButton

// Constantes de diseño
private val ButtonSpacing = 16.dp

@Composable
fun SplashView(
    viewModel: SplashViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToGoogleLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val currentLanguage =
        viewModel.preferencesManager.languageFlow.collectAsState(initial = "en").value

    BaseLayout(
        showAppBar = true,
        appBarTitle = "",
        // Colocamos el fondo transparente y el contenido (iconos/texto) blanco
        appBarBackgroundColor = Color.Transparent,
        appBarContentColor = Color.White,

        appBarActions = {
            MiniIconButton(
                icon = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.splash_change_theme)
            ) {
                viewModel.onThemeChange()
            }
            Spacer(modifier = Modifier.width(8.dp))
            LanguageDropdown(
                currentLanguage = currentLanguage,
                onChangeLanguage = { lang -> viewModel.onLanguageChange(lang) }
            )
        },
        showAppImage = true,
        appImage = {
            AppImage(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Logo",
                size = 150.dp
            )
        }
    ) {
        // Contenido principal del Splash (centro de la pantalla)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SplashButtons(
                onNavigateToLogin = onNavigateToLogin,
                onNavigateToGoogleLogin = onNavigateToGoogleLogin,
                onNavigateToSignUp = onNavigateToSignUp
            )
        }
    }
}

/**
 * Botones principales (Login, Google, SignUp).
 * El cambio de tema e idioma ahora está en el appBar.
 */
@Composable
fun SplashButtons(
    onNavigateToLogin: () -> Unit,
    onNavigateToGoogleLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            text = stringResource(id = R.string.splash_login),
            onClick = onNavigateToLogin
        )
        Spacer(modifier = Modifier.height(ButtonSpacing))

        PrimaryButton(
            text = stringResource(id = R.string.splash_google_login),
            onClick = onNavigateToGoogleLogin,
            icon = painterResource(id = R.drawable.google)
        )
        Spacer(modifier = Modifier.height(ButtonSpacing))

        PrimaryButton(
            text = stringResource(id = R.string.splash_signup),
            onClick = onNavigateToSignUp
        )
    }
}

/**
 * Botón mini con Icon
 */
@Composable
fun MiniIconButton(
    icon: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

/**
 * Menú desplegable para seleccionar idioma.
 * Muestra un botón con el idioma actual y al pulsarlo se abre la lista de idiomas.
 */
@Composable
fun LanguageDropdown(
    currentLanguage: String,
    onChangeLanguage: (String) -> Unit
) {
    val languagesList = listOf("es", "en")
    var expanded by remember { mutableStateOf(false) }

    val languageText = when (currentLanguage) {
        "es" -> stringResource(id = R.string.spanish)
        "en" -> stringResource(id = R.string.english)
        else -> currentLanguage.uppercase()
    }

    OutlinedButton(
        onClick = { expanded = true }
    ) {
        Text(languageText)
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        languagesList.forEach { lang ->
            val itemText = when (lang) {
                "es" -> stringResource(id = R.string.spanish)
                "en" -> stringResource(id = R.string.english)
                else -> lang.uppercase()
            }
            DropdownMenuItem(
                text = { Text(itemText) },
                onClick = {
                    onChangeLanguage(lang)
                    expanded = false
                }
            )
        }
    }
}
