package com.althaus.dev.cinemaNexus.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    // Flujo que emite el idioma configurado
    val languageFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.LANGUAGE] ?: "en" // Valor predeterminado
        }
        .catch { error ->
            logError("Error al leer el idioma", error)
            emit("en") // Emitir valor predeterminado en caso de error
        }

    // Flujo que emite si el modo oscuro está habilitado
    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.DARK_MODE] ?: false // Valor predeterminado
        }
        .catch { error ->
            logError("Error al leer el modo oscuro", error)
            emit(false) // Emitir valor predeterminado en caso de error
        }

    // Combina idioma y modo oscuro en un único flujo
    val settingsFlow: Flow<Pair<String, Boolean>> = combine(
        languageFlow,
        darkModeFlow
    ) { language, darkMode ->
        language to darkMode
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
     * Guarda el idioma seleccionado.
     * @param language Idioma a guardar.
     */
    suspend fun saveLanguage(language: String) {
        savePreference(PreferencesKeys.LANGUAGE, language)
    }

    /**
     * Guarda el estado del modo oscuro.
     * @param isDarkMode Estado del modo oscuro (true/false).
     */
    suspend fun saveDarkMode(isDarkMode: Boolean) {
        savePreference(PreferencesKeys.DARK_MODE, isDarkMode)
    }

    /**
     * Registra un error en Firebase Crashlytics.
     * @param message Mensaje asociado al error.
     * @param throwable Excepción o error ocurrido.
     */
    private fun logError(message: String, throwable: Throwable) {
        FirebaseCrashlytics.getInstance().log(message)
        FirebaseCrashlytics.getInstance().recordException(throwable)
    }
}
