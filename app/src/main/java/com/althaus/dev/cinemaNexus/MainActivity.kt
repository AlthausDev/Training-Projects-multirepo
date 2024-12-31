package com.althaus.dev.cinemaNexus

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.althaus.dev.cinemaNexus.data.PreferencesManager
import com.althaus.dev.cinemaNexus.navigation.NavigationGraph
import com.althaus.dev.cinemaNexus.ui.theme.CinemaNexusTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        observeThemeMode()
        observeLanguageChange()

        setContent {
            CinemaNexusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            NavigationGraph()
                        }
                    }
                )
            }
        }
    }

    private fun observeThemeMode() {
        lifecycleScope.launch {
            preferencesManager.darkModeFlow.collect { isDarkMode ->
                val mode = if (isDarkMode) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
    }

    /**
     * Observa el flujo de idioma desde PreferencesManager y actualiza la configuración de la aplicación.
     */
    private fun observeLanguageChange() {
        lifecycleScope.launch {
            preferencesManager.languageFlow.collectLatest { languageCode ->
                updateLocale(languageCode)
            }
        }
    }

    /**
     * Cambia el idioma de la aplicación dinámicamente.
     * @param languageCode Código del idioma (ejemplo: "en", "es").
     */
    private fun updateLocale(languageCode: String) {
        // Cambiar idioma solo si es necesario
        val currentLocale = Locale.getDefault().language
        if (currentLocale != languageCode) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)

            val config = Configuration(resources.configuration)
            config.setLocale(locale)

            // Actualizamos la configuración de los recursos sin recrear la actividad
            resources.updateConfiguration(config, resources.displayMetrics)
        }
    }

    suspend fun onLanguageSelected(languageCode: String) {
        preferencesManager.saveLanguage(languageCode) // Guardar el idioma seleccionado en preferencias
        updateLocale(languageCode) // Cambiar el idioma en la aplicación
        recreate() // Reiniciar la actividad para que los cambios se apliquen
    }
}
