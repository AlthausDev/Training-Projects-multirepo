package com.althaus.dev.cinemaNexus.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val LANGUAGE = stringPreferencesKey("language")
    val DARK_MODE = booleanPreferencesKey("dark_mode")
}