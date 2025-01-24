package com.althaus.dev.cinemaNexus.data.network.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitConfig @Inject constructor(
    private val apiKeyInterceptor: ApiKeyInterceptor
) {
    fun createRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor) // Añade el interceptor
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") // URL base de la API de TMDB
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
