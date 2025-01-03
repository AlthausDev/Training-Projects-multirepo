package com.althaus.dev.cinemaNexus.data.network.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

/**
 * Servicio para gestionar la autenticación con Firebase.
 */
class AuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    /**
     * Inicia sesión con correo y contraseña.
     *
     * @param email Correo del usuario.
     * @param password Contraseña del usuario.
     * @return [FirebaseUser] si la autenticación es exitosa.
     * @throws Exception Si ocurre un error durante la autenticación.
     */
    suspend fun login(email: String, password: String): FirebaseUser? {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            throw mapFirebaseException(e)
        }
    }

    /**
     * Registra un nuevo usuario con correo y contraseña.
     *
     * @param email Correo del usuario.
     * @param password Contraseña del usuario.
     * @return [FirebaseUser] si el registro es exitoso.
     * @throws Exception Si ocurre un error durante el registro.
     */
    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            throw mapFirebaseException(e)
        }
    }

    /**
     * Envía un correo para restablecer la contraseña.
     *
     * @param email Correo del usuario.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun sendPasswordResetEmail(email: String) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            throw mapFirebaseException(e)
        }
    }

    /**
     * Obtiene el usuario actualmente autenticado.
     *
     * @return [FirebaseUser] si hay un usuario autenticado, `null` de lo contrario.
     */
    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    /**
     * Cierra la sesión del usuario actual.
     */
    fun logout() {
        firebaseAuth.signOut()
    }

    /**
     * Mapea las excepciones de Firebase a excepciones más descriptivas.
     *
     * @param e Excepción de Firebase.
     * @return Excepción personalizada o la original si no hay mapeo.
     */
    private fun mapFirebaseException(e: Exception): Exception {
        return when {
            e.message?.contains("The email address is badly formatted") == true ->
                IllegalArgumentException("El formato del correo es inválido")

            e.message?.contains("There is no user record corresponding to this identifier") == true ->
                IllegalStateException("El usuario no existe")

            e.message?.contains("The password is invalid or the user does not have a password") == true ->
                IllegalArgumentException("La contraseña es incorrecta")

            e.message?.contains("The email address is already in use by another account") == true ->
                IllegalStateException("El correo ya está en uso por otra cuenta")

            else -> e // Retornar la excepción original si no se mapea
        }
    }
}
