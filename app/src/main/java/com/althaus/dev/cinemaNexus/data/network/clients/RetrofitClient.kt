package com.althaus.dev.cinemaNexus.data.network.clients

import com.althaus.dev.cinemaNexus.data.network.config.ApiKeyManager
import com.althaus.dev.cinemaNexus.data.network.services.MovieApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(
    private val apiKeyManager: ApiKeyManager
) {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl = originalRequest.url

                // Agregamos la clave API como parámetro de consulta
                val urlWithApiKey = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", apiKeyManager.getTmdbApiKey())
                    .build()

                val requestWithApiKey = originalRequest.newBuilder().url(urlWithApiKey).build()
                chain.proceed(requestWithApiKey)
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val movieApiService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
