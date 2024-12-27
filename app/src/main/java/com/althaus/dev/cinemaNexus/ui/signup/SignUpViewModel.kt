package com.althaus.dev.cinemaNexus.ui.signup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.AuthService
import com.althaus.dev.cinemaNexus.utils.ValidatorUtil.isEmailValid
import com.althaus.dev.cinemaNexus.utils.ValidatorUtil.isPasswordValid
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    private val _signUpState = mutableStateOf<SignUpState>(SignUpState.Idle)
    val signUpState: State<SignUpState> = _signUpState

    fun signUp() {
        if (!isEmailValid(email)) {
            _signUpState.value = SignUpState.Error("El formato del correo es inválido")
            return
        }

        if (!isPasswordValid(password)) {
            _signUpState.value = SignUpState.Error("La contraseña debe tener al menos 6 caracteres")
            return
        }

        if (password != confirmPassword) {
            _signUpState.value = SignUpState.Error("Las contraseñas no coinciden")
            return
        }

        _signUpState.value = SignUpState.Loading

        viewModelScope.launch {
            try {
                val user = authService.signUp(email, password)
                if (user != null) {
                    _signUpState.value = SignUpState.Success(user)
                } else {
                    _signUpState.value = SignUpState.Error("Error al crear el usuario")
                }
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error("Error inesperado: ${e.message}")
            }
        }
    }
}
