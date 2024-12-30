package com.althaus.dev.cinemaNexus.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.AppImage
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.ClickableText
import com.althaus.dev.cinemaNexus.ui.theme.components.MessageDisplay
import com.althaus.dev.cinemaNexus.ui.theme.components.MessageType
import com.althaus.dev.cinemaNexus.ui.theme.components.PrimaryButton
import com.althaus.dev.cinemaNexus.ui.theme.components.SharedTextField

@Composable
fun SignUpView(
    viewModel: SignUpViewModel,
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToError: (String) -> Unit
) {
    val signUpState = viewModel.signUpState.value

    LaunchedEffect(signUpState) {
        when (signUpState) {
            is SignUpState.Success -> navigateToHome()
            is SignUpState.Error -> navigateToError((signUpState as SignUpState.Error).message)
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
        SignUpContent(
            viewModel = viewModel,
            signUpState = signUpState,
            navigateToLogin = navigateToLogin
        )
    }
}

@Composable
fun SignUpContent(
    viewModel: SignUpViewModel,
    signUpState: SignUpState,
    navigateToLogin: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        // Campos de Entrada
        SharedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = "Ingrese su correo electrónico"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SharedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = "Ingrese su contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        SharedTextField(
            value = viewModel.confirmPassword,
            onValueChange = { viewModel.confirmPassword = it },
            placeholder = "Confirme su contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Registro
        if (signUpState is SignUpState.Loading) {
            CircularProgressIndicator()
        } else {
            PrimaryButton(
                text = "Crear Cuenta",
                onClick = { viewModel.signUp() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de Error
        if (signUpState is SignUpState.Error) {
            MessageDisplay(
                message = (signUpState as SignUpState.Error).message,
                type = MessageType.ERROR,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Texto de Navegación a Login
        ClickableText(
            text = "Ya tienes una cuenta? Inicia sesión",
            onClick = navigateToLogin,
            modifier = Modifier
                .padding(bottom = 24.dp)
        )
    }
}
