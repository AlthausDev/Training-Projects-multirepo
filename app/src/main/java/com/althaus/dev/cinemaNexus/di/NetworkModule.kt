package com.althaus.dev.cinemaNexus.di

import android.content.Context
import com.althaus.dev.cinemaNexus.data.network.config.RetrofitConfig
import com.althaus.dev.cinemaNexus.data.network.services.MovieApiService
import com.althaus.dev.cinemaNexus.data.repository.AuthRepository
import com.althaus.dev.cinemaNexus.data.repository.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Firebase Auth
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // Firebase Firestore
    @Provides
    @Singleton
    fun provideFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // Auth Repository
    @Singleton
    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestoreRepository: FirestoreRepository,
        @ApplicationContext context: Context
    ): AuthRepository = AuthRepository(firebaseAuth, firestoreRepository, context)

    // Firestore Repository
    @Provides
    @Singleton
    fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository =
        FirestoreRepository(firestore)

    @Provides
    @Singleton
    fun provideRetrofit(retrofitConfig: RetrofitConfig): Retrofit {
        return retrofitConfig.createRetrofit()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}
