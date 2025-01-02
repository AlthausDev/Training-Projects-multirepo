package com.althaus.dev.cinemaNexus.di

import android.content.Context
import com.althaus.dev.cinemaNexus.data.repository.AuthRepository
import com.althaus.dev.cinemaNexus.data.repository.FirestoreRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de inyección de dependencias para proporcionar instancias compartidas en toda la aplicación.
 *
 * Este módulo define cómo se crean y gestionan las dependencias clave, como FirebaseAuth,
 * FirebaseFirestore, y los repositorios personalizados.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Proporciona una instancia única de FirebaseAuth.
     *
     * @return Una instancia de [FirebaseAuth].
     */
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Proporciona una instancia única de FirebaseFirestore.
     *
     * @return Una instancia de [FirebaseFirestore].
     */
    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Proporciona una instancia única de AuthRepository.
     *
     * @param firebaseAuth La instancia de FirebaseAuth inyectada.
     * @param firestoreRepository La instancia de FirestoreRepository inyectada.
     * @param context El contexto de la aplicación.
     * @return Una instancia de [AuthRepository].
     */
    @Singleton
    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestoreRepository: FirestoreRepository,
        @ApplicationContext context: Context
    ): AuthRepository = AuthRepository(firebaseAuth, firestoreRepository, context)

    /**
     * Proporciona una instancia única de FirestoreRepository.
     *
     * @param firestore La instancia de FirebaseFirestore inyectada.
     * @return Una instancia de [FirestoreRepository].
     */
    @Provides
    @Singleton
    fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository =
        FirestoreRepository(firestore)

    /**
     * Configura y proporciona una instancia única de GoogleSignInClient.
     *
     * @param context El contexto de la aplicación.
     * @return Una instancia de [GoogleSignInClient].
     * @throws IllegalStateException Si el `default_web_client_id` no está configurado correctamente.
     */

    /**
     * Proporciona una instancia única de UserRepository.
     *
     * @param firebaseAuth La instancia de FirebaseAuth inyectada.
     * @param firestore La instancia de FirebaseFirestore inyectada.
     * @return Una instancia de [UserRepository].
     */
}