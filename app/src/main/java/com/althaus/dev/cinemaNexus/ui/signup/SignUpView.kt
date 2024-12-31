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
import androidx.compose.runtime.collectAsState
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
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onError: (String) -> Unit
) {
    val signUpState = viewModel.signUpState.collectAsState()

    LaunchedEffect(key1 = signUpState.value) {
        when (val state = signUpState.value) {
            is SignUpState.Success -> onNavigateToHome()
            is SignUpState.Error -> onError(state.message)
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
        SignUpContent(
            viewModel = viewModel,
            onNavigateToLogin = onNavigateToLogin
        )
    }
}

@Composable
fun SignUpContent(
    viewModel: SignUpViewModel,
    onNavigateToLogin: () -> Unit
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()
    val signUpState = viewModel.signUpState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        SharedTextField(
            value = email.value,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = "Ingrese su correo electrónico"
        )

        Spacer(modifier = Modifier.height(16.dp))

        SharedTextField(
            value = password.value,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = "Ingrese su contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        SharedTextField(
            value = confirmPassword.value,
            onValueChange = { viewModel.updateConfirmPassword(it) },
            placeholder = "Confirme su contraseña",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (signUpState.value is SignUpState.Loading) {
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
                message = (signUpState.value as SignUpState.Error).message,
                type = MessageType.ERROR,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            text = "¿Ya tienes una cuenta? Inicia sesión",
            onClick = onNavigateToLogin,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}
