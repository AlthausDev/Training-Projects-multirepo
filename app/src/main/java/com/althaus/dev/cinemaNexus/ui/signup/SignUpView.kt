package com.althaus.dev.cinemaNexus.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.althaus.dev.cinemaNexus.R
import com.althaus.dev.cinemaNexus.ui.theme.components.AppImage
import com.althaus.dev.cinemaNexus.ui.theme.components.BaseLayout

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
        showAppBar = false
    ) {
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        )
        {}
    }
}
