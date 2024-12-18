package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.runtime.Composable
import com.althaus.dev.cinemaNexus.ui.theme.components.SharedTextField

@Composable
fun LoginView(
    viewModel: LoginViewModel
) {
    SharedTextField(
        value = viewModel.email,
        onValueChange = { viewModel.email = it },
        placeholder = "Email"
    )

    SharedTextField(
        value = viewModel.password,
        onValueChange = { viewModel.password = it },
        placeholder = "Password"
    )
}

