package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.*

@Composable
fun LoginView(
    viewModel: LoginViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onError: (String) -> Unit
) {
    val loginState = viewModel.loginState.collectAsState()

    LaunchedEffect(key1 = loginState.value) {
        when (val state = loginState.value) {
            is LoginState.Success -> onNavigateToHome()
            is LoginState.Error -> onError(state.message)
            else -> {}
        }
    }

    BaseLayout(
        verticalArrangement = Arrangement.SpaceBetween,
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
            onNavigateToSignUp = onNavigateToSignUp
        )
    }
}

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    onNavigateToSignUp: () -> Unit
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val loginState = viewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {

        // Email Field
        SharedTextField(
            value = email.value,
            onValueChange = viewModel::updateEmail,
            placeholder = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        SharedTextField(
            value = password.value,
            onValueChange = viewModel::updatePassword,
            placeholder = "Contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        when (loginState.value) {
            is LoginState.Loading -> CircularProgressIndicator()
            else -> PrimaryButton(
                text = "Iniciar Sesión",
                onClick = { viewModel.login() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigate to Sign-Up Button
        ClickableText(
            text = "¿No tienes una cuenta? Regístrate",
            onClick = onNavigateToSignUp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Error Message
        if (loginState.value is LoginState.Error) {
            val errorMessage = (loginState.value as LoginState.Error).message
            MessageDisplay(
                message = errorMessage,
                type = MessageType.ERROR,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
