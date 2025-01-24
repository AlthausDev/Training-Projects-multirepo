package com.althaus.dev.cinemaNexus.ui.signup

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
 * Representa los posibles estados del registro de usuario.
 */
sealed class SignUpState {
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val user: FirebaseUser) : SignUpState()
    data class Error(val message: String) : SignUpState()
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

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
     * Actualiza el valor de la confirmación de la contraseña.
     */
    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    /**
     * Maneja el registro de un nuevo usuario.
     */
    fun signUp() {
        // Validar correo electrónico
        if (!isEmailValid(_email.value)) {
            _signUpState.value = SignUpState.Error("El formato del correo es inválido")
            return
        }

        // Validar contraseña
        if (!isPasswordValid(_password.value)) {
            _signUpState.value = SignUpState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        // Validar coincidencia de contraseñas
        if (_password.value != _confirmPassword.value) {
            _signUpState.value = SignUpState.Error("Las contraseñas no coinciden")
            return
        }

        // Cambiar estado a cargando
        _signUpState.value = SignUpState.Loading

        // Intentar registro
        viewModelScope.launch {
            try {
                val user = authService.signUp(_email.value, _password.value)
                _signUpState.value = user?.let { SignUpState.Success(it) }
                    ?: SignUpState.Error("Error al crear el usuario")
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(mapError(e))
            }
        }
    }

    /**
     * Resetea el estado del registro a Idle.
     */
    fun resetState() {
        _signUpState.value = SignUpState.Idle
    }

    /**
     * Mapea excepciones a mensajes de error más descriptivos.
     */
    private fun mapError(e: Exception): String {
        return when {
            e.message?.contains("The email address is badly formatted") == true ->
                "El correo electrónico tiene un formato inválido."

            e.message?.contains("The email address is already in use by another account") == true ->
                "El correo ya está registrado."

            e.message?.contains("The password is invalid or the user does not have a password") == true ->
                "La contraseña es inválida."

            else -> e.localizedMessage ?: "Ocurrió un error desconocido."
        }
    }
}
