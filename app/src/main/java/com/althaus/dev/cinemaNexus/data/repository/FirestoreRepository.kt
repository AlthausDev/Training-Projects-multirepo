package com.althaus.dev.cinemaNexus.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Repositorio para interactuar con Firestore en la aplicación Cinema Nexus.
 *
 * Maneja operaciones relacionadas con usuarios, reservas y películas.
 */
class FirestoreRepository @Inject constructor(
    private val db: FirebaseFirestore
) {
    private val usersCollection = db.collection("users")
    private val reservationsCollection = db.collection("reservations")
    private val moviesCollection = db.collection("movies")
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: "unknown-user"

    // ---- Métodos generales ----

    /**
     * Genera un nuevo ID único para una colección específica.
     *
     * @param collection Nombre de la colección.
     * @return ID único generado.
     */
    fun generateNewId(collection: String): String = db.collection(collection).document().id

    // ---- Métodos de Usuarios ----

    /**
     * Guarda un nuevo usuario en Firestore.
     *
     * @param userId ID del usuario.
     * @param userData Mapa con los datos del usuario.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun saveUser(userId: String, userData: Map<String, Any>) {
        try {
            usersCollection.document(userId).set(userData).await()
        } catch (e: Exception) {
            throw Exception("Error al guardar el usuario: ${e.localizedMessage}")
        }
    }


    /**
     * Actualiza datos específicos de un usuario.
     *
     * @param userId ID del usuario.
     * @param updates Mapa con los campos y valores a actualizar.
     * @return `true` si la actualización fue exitosa, `false` si falló.
     */
    suspend fun updateUser(userId: String, updates: Map<String, Any>): Boolean {
        return try {
            usersCollection.document(userId).update(updates).await()
            true
        } catch (e: FirebaseFirestoreException) {
            println("Error específico de Firestore: ${e.code}, ${e.message}")
            false
        } catch (e: Exception) {
            println("Error al actualizar usuario $userId: ${e.message}")
            false
        }
    }

    /**
     * Obtiene los datos de un usuario por su ID.
     *
     * @param userId ID del usuario.
     * @return Mapa con los datos del usuario o `null` si no existe.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun getUser(userId: String): Map<String, Any>? {
        return try {
            val document = usersCollection.document(userId).get().await()
            document.data
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al obtener el usuario: ${e.message}", e)
        }
    }

    // ---- Métodos de Reservas ----

    /**
     * Guarda una nueva reserva en Firestore.
     *
     * @param reservationId ID de la reserva.
     * @param reservationData Datos de la reserva como mapa.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun saveReservation(reservationId: String, reservationData: Map<String, Any>) {
        try {
            reservationsCollection.document(reservationId).set(reservationData).await()
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al guardar la reserva: ${e.message}", e)
        }
    }

    /**
     * Obtiene las reservas de un usuario específico.
     *
     * @param userId ID del usuario.
     * @return Lista de mapas con las reservas.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    suspend fun getUserReservations(userId: String): List<Map<String, Any>> {
        return try {
            reservationsCollection.whereEqualTo("userId", userId).get().await()
                .documents.mapNotNull { it.data }
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al obtener las reservas del usuario: ${e.message}", e)
        }
    }

    /**
     * Elimina una reserva de Firestore.
     *
     * @param reservationId ID de la reserva.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun deleteReservation(reservationId: String) {
        try {
            reservationsCollection.document(reservationId).delete().await()
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al eliminar la reserva: ${e.message}", e)
        }
    }

    // ---- Métodos de Películas ----

    /**
     * Guarda información de una película en Firestore.
     *
     * @param movieId ID de la película.
     * @param movieData Datos de la película como mapa.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun saveMovie(movieId: String, movieData: Map<String, Any>) {
        try {
            moviesCollection.document(movieId).set(movieData).await()
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al guardar la película: ${e.message}", e)
        }
    }

    /**
     * Obtiene los datos de una película por su ID.
     *
     * @param movieId ID de la película.
     * @return Mapa con los datos de la película o `null` si no existe.
     * @throws Exception Si ocurre un error durante la operación.
     */
    suspend fun getMovie(movieId: String): Map<String, Any>? {
        return try {
            val document = moviesCollection.document(movieId).get().await()
            document.data
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al obtener la película: ${e.message}", e)
        }
    }

    /**
     * Obtiene todas las películas almacenadas en Firestore.
     *
     * @return Lista de mapas con las películas.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    suspend fun getAllMovies(): List<Map<String, Any>> {
        return try {
            moviesCollection.get().await().documents.mapNotNull { it.data }
        } catch (e: FirebaseFirestoreException) {
            throw Exception("Error al obtener las películas: ${e.message}", e)
        }
    }
}
