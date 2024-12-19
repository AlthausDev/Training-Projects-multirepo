package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    navigateToSingUp: () -> Unit,
    navigateToError: () -> Unit
) {
    BaseLayout(
        verticalArragement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caja para el logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center // Centra el logo en su área
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
            contentAlignment = Alignment.Center // Centra el contenido en su área
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
                    placeholder = "Password"
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    text = "Login",
                    onClick = navigateToHome
                )
            }
        }
    }
}