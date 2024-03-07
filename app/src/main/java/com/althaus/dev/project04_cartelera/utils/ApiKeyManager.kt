package com.althaus.dev.project04_cartelera.utils

import android.content.Context
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.base.App

object ApiKeyManager {


    fun getTmdbApiKey(): String {
        val context = App.instance.applicationContext
        val resources = context.resources
        val apiKey = resources.getString(R.string.tmdb_api_key)
        return apiKey
    }
}
