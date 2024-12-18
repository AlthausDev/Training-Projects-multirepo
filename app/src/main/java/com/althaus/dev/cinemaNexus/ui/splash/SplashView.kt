package com.althaus.dev.cinemaNexus.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.PrimaryButton


@Composable
fun SplashView(
    viewModel: SplashViewModel,
    navigateToLogin: () -> Unit
) {
    BaseLayout(
        verticalArragement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            text = "Iniciar Sesión",
            onClick = navigateToLogin
        )
    }
}
