package com.althaus.dev.cinemaNexus.data.network.config

import android.content.Context
import com.althaus.dev.cinemaNexus.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ApiKeyManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getTmdbApiKey(): String {
        return context.getString(R.string.tmdb_api_key)
    }
}
