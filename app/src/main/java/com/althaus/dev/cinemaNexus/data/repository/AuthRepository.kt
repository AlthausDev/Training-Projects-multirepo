package com.althaus.dev.cinemaNexus.data.repository

import android.content.Context
import com.althaus.dev.cinemaNexus.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Clase sellada que representa los posibles resultados de las operaciones de autenticación.
 */
sealed class AuthResult {
    data class Success(val user: FirebaseUser) : AuthResult()
    data class Failure(val exception: Exception) : AuthResult()
    object UserNotFound : AuthResult()
}

/**
 * Repositorio para gestionar la autenticación de usuarios en Firebase, incluyendo Google Sign-In.
 */
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestoreRepository: FirestoreRepository,
    @ApplicationContext private val context: Context
) {

    /**
     * Obtiene el usuario actualmente autenticado.
     *
     * @return [FirebaseUser] si hay un usuario autenticado, `null` de lo contrario.
     */
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    /**
     * Verifica si hay un usuario autenticado.
     *
     * @return `true` si hay un usuario autenticado, `false` de lo contrario.
     */
    fun isUserAuthenticated(): Boolean = currentUser != null

    /**
     * Verifica si el correo electrónico del usuario autenticado está verificado.
     *
     * @return `true` si el correo está verificado, `false` si no.
     */
    fun isEmailVerified(): Boolean = currentUser?.isEmailVerified ?: false

    /**
     * Configura el cliente de Google Sign-In.
     */
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    /**
     * Obtiene el cliente de Google Sign-In configurado.
     *
     * @return [GoogleSignInClient] listo para iniciar sesión.
     */
    //fun getGoogleSignInClient(): GoogleSignInClient = googleSignInClient

    /**
     * Devuelve un intent para iniciar el flujo de Google Sign-In.
     */
    //fun getGoogleSignInIntent() = googleSignInClient.signInIntent

    /**
     * Inicia sesión con un token de ID de Google.
     *
     * @param idToken Token de ID proporcionado por Google.
     * @return [AuthResult] con el resultado de la operación.
     */
    suspend fun signInWithGoogle(idToken: String): AuthResult = safeAuthCall {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).await()?.user
    }

    /**
     * Inicia sesión con una cuenta de Google.
     *
     * @param account [GoogleSignInAccount] obtenida tras el flujo de Google Sign-In.
     * @return [AuthResult] con el resultado de la operación.
     */
    suspend fun signInWithGoogleAccount(account: GoogleSignInAccount): AuthResult {
        return signInWithGoogle(account.idToken ?: "").also {
            if (it is AuthResult.Failure) {
                println("Google Sign-In falló: ${it.exception.message}")
            }
        }
    }

    /**
     * Cierra sesión tanto en Firebase como en Google.
     */
    fun signOutFromGoogle() {
        googleSignInClient.signOut()
        firebaseAuth.signOut()
    }

    /**
     * Inicia sesión con correo y contraseña.
     *
     * @param email Correo del usuario.
     * @param password Contraseña del usuario.
     * @return [AuthResult] indicando el resultado de la operación.
     */
    suspend fun login(email: String, password: String): AuthResult = safeAuthCall {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()?.user
    }

    /**
     * Registra un nuevo usuario con correo y contraseña.
     *
     * @param email Correo del usuario.
     * @param password Contraseña del usuario.
     * @param name Nombre del usuario (opcional).
     * @return [AuthResult] indicando el resultado de la operación.
     */
    suspend fun register(email: String, password: String, name: String? = null): AuthResult {
        return safeAuthCall {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result?.user
            if (user != null) {
                // Crear el mapa con los datos del usuario
                val userData = mapOf(
                    "id" to user.uid,
                    "name" to (name ?: user.displayName ?: "Usuario") as String,
                    "email" to email as String,
                    "profileImage" to null as Any, // Sin imagen de perfil por defecto
                    "creationDate" to System.currentTimeMillis() as Any, // Fecha de creación como timestamp
                    "isVerified" to user.isEmailVerified as Any
                )
                // Guardar los datos del usuario en Firestore
                firestoreRepository.saveUser(user.uid, userData)
            }
            user
        }
    }


    /**
     * Envía un correo para restablecer la contraseña.
     *
     * @param email Correo del usuario.
     * @return `true` si se envía correctamente, `false` de lo contrario.
     */
    suspend fun sendPasswordResetEmail(email: String): Boolean {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            println("Error al enviar correo de restablecimiento: ${e.message}")
            false
        }
    }

    /**
     * Reautentica al usuario actual.
     *
     * @param password Contraseña actual del usuario.
     * @return [AuthResult] indicando el resultado de la operación.
     */
    suspend fun reAuthenticate(password: String): AuthResult = safeAuthCall {
        val email = currentUser?.email ?: throw IllegalStateException("Correo no disponible")
        val credential = EmailAuthProvider.getCredential(email, password)
        currentUser?.reauthenticate(credential)?.await()
        currentUser
    }

    /**
     * Cierra sesión del usuario actual.
     */
    fun logout() {
        firebaseAuth.signOut()
    }

    /**
     * Maneja llamadas seguras para operaciones de autenticación.
     *
     * @param authCall Operación de autenticación a ejecutar.
     * @return [AuthResult] con el resultado de la operación.
     */
    private suspend fun safeAuthCall(authCall: suspend () -> FirebaseUser?): AuthResult {
        return try {
            authCall()?.let { AuthResult.Success(it) } ?: AuthResult.UserNotFound
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            AuthResult.Failure(Exception("Credenciales inválidas.", e))
        } catch (e: FirebaseAuthInvalidUserException) {
            AuthResult.Failure(Exception("Usuario no encontrado.", e))
        } catch (e: Exception) {
            AuthResult.Failure(Exception("Error inesperado: ${e.message}", e))
        }
    }
}
