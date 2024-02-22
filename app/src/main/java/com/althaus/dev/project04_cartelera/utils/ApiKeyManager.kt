package com.althaus.dev.project04_cartelera.utils

import java.io.FileInputStream
import java.util.Properties

object ApiKeyManager {

    fun getTmdbApiKey(): String {
        val properties = Properties()
        val fileInputStream = FileInputStream("local.properties")
        properties.load(fileInputStream)
        return properties.getProperty("tmdb.api.key")
    }
}
