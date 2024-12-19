package com.althaus.dev.cinemaNexus.ui.login

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.althaus.dev.cinemaNexus.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService: AuthService) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun login(email: String, password: String) {

    }
}
