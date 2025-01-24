package com.althaus.dev.cinemaNexus.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.cinemaNexus.data.datastore.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val preferencesManager: PreferencesManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun onThemeChange() {
        viewModelScope.launch {
            val isDarkMode = preferencesManager.darkModeFlow.first() // Obtiene el valor actual
            preferencesManager.saveDarkMode(!isDarkMode) // Guarda el cambio
        }
    }

    // Cambiar el idioma (español / inglés)
    fun onLanguageChange(languageCode: String) {
        viewModelScope.launch {
            preferencesManager.saveLanguage(languageCode)
            // updateLocale(languageCode)
        }
    }
}
