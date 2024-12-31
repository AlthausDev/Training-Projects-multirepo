package com.althaus.dev.cinemaNexus.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.AuthService
import com.althaus.dev.cinemaNexus.utils.ValidatorUtil.isEmailValid
import com.althaus.dev.cinemaNexus.utils.ValidatorUtil.isPasswordValid
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        if (!isEmailValid(_email.value)) {
            _loginState.value = LoginState.Error("El formato del correo es inválido")
            return
        }

        if (!isPasswordValid(_password.value)) {
            _loginState.value = LoginState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val user = authService.login(_email.value, _password.value)
                _loginState.value = user?.let { LoginState.Success(it) }
                    ?: LoginState.Error("Credenciales inválidas")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.localizedMessage ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}
