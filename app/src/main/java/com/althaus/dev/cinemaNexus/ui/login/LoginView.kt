package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.AppImage
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.PrimaryButton
import com.althaus.dev.cinemaNexus.ui.theme.components.SharedTextField

@Composable
fun LoginView(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToError: (String) -> Unit
) {
    val loginState by viewModel.loginState

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> navigateToHome()
            is LoginState.Error -> navigateToError((loginState as LoginState.Error).message)
            else -> {}
        }
    }

    BaseLayout(
        verticalArragement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caja para el logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AppImage(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Logo de la aplicación",
                size = 150.dp
            )
        }

        // Caja para los campos de texto y el botón
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SharedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    placeholder = "Email"
                )
                Spacer(modifier = Modifier.height(16.dp))
                SharedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = "Contraseña",
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator()
                } else {
                    PrimaryButton(
                        text = "Iniciar Sesión",
                        onClick = { viewModel.login() }
                    )
                }
            }
        }

        // Mostrar mensaje de error si existe
        if (loginState is LoginState.Error) {
            Text(
                text = (loginState as LoginState.Error).message,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                color = androidx.compose.ui.graphics.Color.Red // Color de error
            )
        }
    }
}
