package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.*

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
        horizontalAlignment = Alignment.CenterHorizontally,
        showAppBar = false,
        showAppImage = true,
        appImage = {
            AppImage(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Logo de la aplicación",
                size = 150.dp
            )
        }
    ) {
        LoginContent(
            viewModel = viewModel,
            loginState = loginState,
            navigateToSignUp = navigateToSignUp
        )
    }
}

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    loginState: LoginState,
    navigateToSignUp: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Email Field
        SharedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        SharedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = "Contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        when (loginState) {
            is LoginState.Loading -> CircularProgressIndicator()
            else -> PrimaryButton(
                text = "Iniciar Sesión",
                onClick = { viewModel.login() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigate to Sign-Up Button
        ClickableText(
            text = "No tienes una cuenta? Registrate",
            onClick = navigateToSignUp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Error Message
        if (loginState is LoginState.Error) {
            MessageDisplay(
                message = loginState.message,
                type = MessageType.ERROR,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
