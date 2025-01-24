package com.althaus.dev.cinemaNexus.data.repository

import com.althaus.dev.cinemaNexus.data.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Representa los posibles resultados de las operaciones relacionadas con usuarios.
 */
sealed class UserResult {
    data class Success(val user: UserProfile) : UserResult()
    data class Failure(val exception: Exception) : UserResult()
    object UserNotFound : UserResult()
}

/**
 * Repositorio que gestiona las operaciones relacionadas con usuarios en Firebase.
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
     * Verifica si hay un usuario autenticado.
     *
     * @return `true` si hay un usuario autenticado, `false` en caso contrario.
     */
    fun isUserAuthenticated(): Boolean {
        return currentUser != null
    }

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
            val userProfile = snapshot.toObject(UserProfile::class.java)
            userProfile?.let { UserResult.Success(it) } ?: UserResult.UserNotFound
        } catch (e: FirebaseFirestoreException) {
            UserResult.Failure(Exception("Error de Firestore: ${e.message}", e))
        } catch (e: Exception) {
            UserResult.Failure(Exception("Error desconocido: ${e.message}", e))
        }
    }

    /**
     * Crea o actualiza un perfil de usuario en Firestore.
     *
     * @param userProfile El perfil del usuario a guardar.
     * @return [UserResult.Success] si se guarda correctamente,
     * [UserResult.Failure] si ocurre un error durante la operación.
     */
    suspend fun saveUserProfile(userProfile: UserProfile): UserResult {
        return try {
            firestore.collection("users").document(userProfile.id).set(userProfile.toMap()).await()
            UserResult.Success(userProfile)
        } catch (e: FirebaseFirestoreException) {
            UserResult.Failure(
                Exception(
                    "Error al guardar el perfil en Firestore: ${e.message}",
                    e
                )
            )
        } catch (e: Exception) {
            UserResult.Failure(Exception("Error desconocido al guardar el perfil: ${e.message}", e))
        }
    }

    /**
     * Elimina un perfil de usuario de Firestore.
     *
     * @param userId El ID del usuario a eliminar.
     * @return `true` si se elimina correctamente, `false` si ocurre un error.
     */
    suspend fun deleteUserProfile(userId: String): Boolean {
        return try {
            firestore.collection("users").document(userId).delete().await()
            true
        } catch (e: FirebaseFirestoreException) {
            println("Error al eliminar el perfil: ${e.message}")
            false
        } catch (e: Exception) {
            println("Error desconocido al eliminar el perfil: ${e.message}")
            false
        }
    }
}
