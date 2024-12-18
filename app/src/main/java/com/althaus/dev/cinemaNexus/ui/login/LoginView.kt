package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout
import com.althaus.dev.cinemaNexus.ui.theme.components.SharedTextField

@Composable
fun LoginView(
    viewModel: LoginViewModel
) {
    BaseLayout(
        verticalArragement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SharedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            placeholder = "Email"
        )
        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre campos
        SharedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = "Password"
        )
    }
}
