package com.althaus.dev.cinemaNexus.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                onNavigateToSignUp = onNavigateToSignUp
            )
        }
    }
}

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
            text = "Iniciar Sesión",
            onClick = onNavigateToLogin
        )

        Spacer(modifier = Modifier.height(ButtonSpacing))

        PrimaryButton(
            text = "Iniciar con Google",
            onClick = onNavigateToGoogleLogin,
            icon = painterResource(id = R.drawable.google)
        )

        Spacer(modifier = Modifier.height(ButtonSpacing))

        PrimaryButton(
            text = "Crear Cuenta",
            onClick = onNavigateToSignUp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashViewPreview() {
    SplashView(
        viewModel = SplashViewModel(), // Simula un ViewModel vacío para la vista previa
        onNavigateToLogin = {},
        onNavigateToGoogleLogin = {},
        onNavigateToSignUp = {}
    )
}
