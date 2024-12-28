package com.althaus.dev.cinemaNexus.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun SignUpView(
    viewModel: SignUpViewModel,
    navigateToHome: () -> Unit,
    navtigateToLogin: () -> Unit,
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
                contentDescription = "Logo",
                size = 150.dp
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

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

                if (signUpState is SignUpState.Loading) {
                    CircularProgressIndicator()
                } else {
                    PrimaryButton(
                        text = "Crear Cuenta",
                        onClick = viewModel::signUp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (signUpState is SignUpState.Error) {
                    Text(
                        text = (signUpState as SignUpState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            }
        }
    }
}
