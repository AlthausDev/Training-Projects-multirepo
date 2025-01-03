package com.althaus.dev.cinemaNexus.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.core.validation.ValidatorUtil.isEmailValid
import com.althaus.dev.cinemaNexus.core.validation.ValidatorUtil.isPasswordValid
import com.althaus.dev.cinemaNexus.data.network.services.AuthService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Representa los posibles estados del inicio de sesión.
 */
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

    // Flujos para el correo electrónico y la contraseña
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    // Flujo para el estado del inicio de sesión
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    /**
     * Actualiza el valor del correo electrónico.
     */
    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    /**
     * Actualiza el valor de la contraseña.
     */
    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    /**
     * Maneja el inicio de sesión con las credenciales proporcionadas.
     */
    fun login() {
        // Validar correo electrónico
        if (!isEmailValid(_email.value)) {
            _loginState.value = LoginState.Error("El formato del correo es inválido")
            return
        }

        // Validar contraseña
        if (!isPasswordValid(_password.value)) {
            _loginState.value = LoginState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        // Cambiar estado a cargando
        _loginState.value = LoginState.Loading

        // Intentar inicio de sesión
        viewModelScope.launch {
            try {
                val user = authService.login(_email.value, _password.value)
                _loginState.value = user?.let { LoginState.Success(it) }
                    ?: LoginState.Error("Credenciales inválidas")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(mapError(e))
            }
        }
    }

    /**
     * Resetea el estado del inicio de sesión a Idle.
     */
    fun resetState() {
        _loginState.value = LoginState.Idle
    }

    /**
     * Mapea excepciones a mensajes de error más descriptivos.
     */
    private fun mapError(e: Exception): String {
        return when {
            e.message?.contains("The email address is badly formatted") == true ->
                "El correo electrónico tiene un formato inválido."

            e.message?.contains("The password is invalid or the user does not have a password") == true ->
                "La contraseña es incorrecta."

            e.message?.contains("There is no user record corresponding to this identifier") == true ->
                "El usuario no existe."

            else -> e.localizedMessage ?: "Ocurrió un error desconocido."
        }
    }
}
