package com.althaus.dev.cinemaNexus.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dataStore = context.dataStore

    // Lee el idioma desde las preferencias guardadas
    val languageFlow: Flow<String> = dataStore.data
        .catch { exception ->
            handleError("Error al leer el idioma", exception)
            emit(emptyPreferences()) // Emite un valor vacío si hay error
        }
        .map { preferences ->
            preferences[PreferencesKeys.LANGUAGE]
                ?: Locale.getDefault().language
        }

    // Guarda el idioma seleccionado
    suspend fun saveLanguage(language: String) {
        savePreference(PreferencesKeys.LANGUAGE, language)
    }

    // Flujo que emite si el modo oscuro está habilitado
    val darkModeFlow: Flow<Boolean> = dataStore.data
        .catch { error ->
            handleError("Error al leer el modo oscuro", error)
            emit(emptyPreferences())
        }
        .map { preferences ->
            // True si la preferencia está en true, False en caso contrario
            preferences[PreferencesKeys.DARK_MODE] == true
        }

    /**
     * Método genérico para guardar preferencias.
     * @param key Clave de la preferencia.
     * @param value Valor a guardar.
     */
    private suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * Guarda el estado del modo oscuro.
     * @param isDarkMode Estado del modo oscuro (true/false).
     */
    suspend fun saveDarkMode(isDarkMode: Boolean) {
        savePreference(PreferencesKeys.DARK_MODE, isDarkMode)
    }

    /**
     * Maneja y registra errores en Firebase Crashlytics.
     * @param message Mensaje asociado al error.
     * @param throwable Excepción o error ocurrido.
     */
    private fun handleError(message: String, throwable: Throwable) {
        FirebaseCrashlytics.getInstance().log(message)
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }
}
