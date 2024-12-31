package com.althaus.dev.cinemaNexus.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.AppImage
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.PrimaryButton

// Constantes de diseño
private val ButtonSpacing = 16.dp
private val ScreenPadding = 16.dp

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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        showAppBar = false,
        showAppImage = true,
        appImage = {
            AppImage(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Logo",
                size = 150.dp
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(ScreenPadding)
    ) {
        // Caja para los botones
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SplashButtons(
                onNavigateToLogin = onNavigateToLogin,
                onNavigateToGoogleLogin = onNavigateToGoogleLogin,
                onNavigateToSignUp = onNavigateToSignUp,
                onChangeTheme = { viewModel.onThemeChange() },
                onChangeLanguage = { languageCode -> viewModel.onLanguageChange(languageCode) },
                currentLanguage = currentLanguage
            )
        }
    }
}

@Composable
fun SplashButtons(
    onNavigateToLogin: () -> Unit,
    onNavigateToGoogleLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onChangeTheme: () -> Unit,
    onChangeLanguage: (String) -> Unit,
    currentLanguage: String // Añadido para cambiar entre "es" y "en"
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

        Spacer(modifier = Modifier.height(ButtonSpacing))

        // Botón para cambiar el tema (Modo oscuro / Modo claro)
        PrimaryButton(
            text = stringResource(id = R.string.splash_change_theme),
            onClick = onChangeTheme
        )

        Spacer(modifier = Modifier.height(ButtonSpacing))

        // Botón para cambiar el idioma (Español / Inglés)
        PrimaryButton(
            text = stringResource(id = R.string.splash_change_language),
            onClick = {
                val newLanguage = if (currentLanguage == "es") "en" else "es"
                onChangeLanguage(newLanguage)
            }
        )
    }
}
