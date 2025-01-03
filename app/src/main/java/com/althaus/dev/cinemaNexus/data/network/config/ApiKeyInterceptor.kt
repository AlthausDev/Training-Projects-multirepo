package com.althaus.dev.cinemaNexus.data.network.config

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val apiKeyManager: ApiKeyManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", apiKeyManager.getTmdbApiKey()) // Añade la clave API
            .build()
        val newRequest = originalRequest.newBuilder().url(urlWithApiKey).build()
        return chain.proceed(newRequest)
    }
}
