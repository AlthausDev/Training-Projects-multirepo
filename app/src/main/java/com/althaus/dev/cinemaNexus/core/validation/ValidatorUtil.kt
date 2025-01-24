package com.althaus.dev.cinemaNexus.core.validation

import android.accounts.NetworkErrorException
import android.util.Patterns
import com.althaus.dev.cinemaNexus.ui.login.LoginState

object ValidatorUtil {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    fun handleError(e: Exception): LoginState.Error {
        return when (e) {
            is NetworkErrorException -> LoginState.Error("Error de red. Por favor, revisa tu conexión.")
            else -> LoginState.Error("Error inesperado: ${e.message}")
        }
    }
}
