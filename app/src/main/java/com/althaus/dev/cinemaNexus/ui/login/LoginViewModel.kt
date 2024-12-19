package com.althaus.dev.cinemaNexus.ui.login

import android.accounts.NetworkErrorException
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.AuthService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: FirebaseUser) : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _loginState = mutableStateOf<LoginState>(LoginState.Idle)
    val loginState: State<LoginState> = _loginState

    fun login() {
        if (!isEmailValid(email)) {
            _loginState.value = LoginState.Error("El formato del correo es inválido")
            return
        }

        if (!isPasswordValid(password)) {
            _loginState.value = LoginState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val user = authService.login(email, password)
                if (user != null) {
                    _loginState.value = LoginState.Success(user)
                } else {
                    _loginState.value = LoginState.Error("Credenciales inválidas")
                }
            } catch (e: IllegalArgumentException) {
                _loginState.value = LoginState.Error(e.message ?: "Error desconocido")
            } catch (e: IllegalStateException) {
                _loginState.value = LoginState.Error(e.message ?: "Error desconocido")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error inesperado: ${e.message}")
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    private fun handleError(e: Exception): LoginState.Error {
        return when (e) {
            is NetworkErrorException -> LoginState.Error("Error de red. Por favor, revisa tu conexión.")
            else -> LoginState.Error("Error inesperado: ${e.message}")
        }
    }
}
