package com.althaus.dev.cinemaNexus.data.network.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthService @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    suspend fun login(email: String, password: String): FirebaseUser? {
        try {
            return firebaseAuth.signInWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            throw mapFirebaseException(e)
        }
    }

    suspend fun signUp(email: String, password: String): FirebaseUser? {
        try {
            return firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            throw mapFirebaseException(e)
        }
    }

    private fun mapFirebaseException(e: Exception): Exception {
        return when {
            e.message?.contains("The email address is badly formatted") == true ->
                IllegalArgumentException("El formato del correo es inválido")

            e.message?.contains("There is no user record corresponding to this identifier") == true ->
                IllegalStateException("El usuario no existe")

            e.message?.contains("The password is invalid or the user does not have a password") == true ->
                IllegalArgumentException("La contraseña es incorrecta")

            else -> e // Retornar la excepción original si no se mapea
        }
    }
}