package com.althaus.dev.cinemaNexus.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.jvm.java
import kotlin.let

/**
 * Representa los posibles resultados de las operaciones relacionadas con usuarios.
 */
sealed class UserResult {
    /**
     * Indica que la operación fue exitosa.
     *
     * @param user El perfil del usuario obtenido o actualizado.
     */
    data class Success(val user: com.althaus.dev.cinemaNexus.data.model.UserProfile) : UserResult()

    /**
     * Indica que la operación falló debido a una excepción.
     *
     * @param exception La excepción generada durante la operación.
     */
    data class Failure(val exception: kotlin.Exception) : UserResult()

    /**
     * Indica que el usuario solicitado no fue encontrado.
     */
    object UserNotFound : UserResult()
}

/**
 * Repositorio que gestiona las operaciones relacionadas con usuarios en Firebase.
 *
 * @property firebaseAuth Proporciona autenticación de usuarios con Firebase.
 * @property firestore Proporciona acceso a la base de datos de Firestore.
 */
class UserRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    /**
     * Obtiene el usuario autenticado actualmente en Firebase Authentication.
     *
     * @return El usuario autenticado actualmente o `null` si no hay un usuario autenticado.
     */
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser


    /**
     * Obtiene el perfil de un usuario desde Firestore.
     *
     * @param userId El ID del usuario cuyo perfil se desea obtener.
     * @return [UserResult.Success] con el perfil del usuario si se encuentra,
     * [UserResult.UserNotFound] si el perfil no existe, o
     * [UserResult.Failure] si ocurre un error durante la operación.
     */
    suspend fun getUserProfile(userId: String): UserResult {
        return try {
            val snapshot = firestore.collection("users").document(userId).get().await()
            val userProfile =
                snapshot.toObject(com.althaus.dev.cinemaNexus.data.model.UserProfile::class.java)
            userProfile?.let { UserResult.Success(it) } ?: UserResult.UserNotFound
        } catch (e: kotlin.Exception) {
            UserResult.Failure(e)
        }
    }
}
