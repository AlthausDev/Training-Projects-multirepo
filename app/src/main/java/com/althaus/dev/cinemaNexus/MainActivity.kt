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

        // Observar cambios de tema e idioma
        observeThemeMode()
        observeLanguageChange()

        // Configurar la interfaz
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

    /**
     * Observa el flujo de modo oscuro desde PreferencesManager y actualiza el tema de la aplicación.
     */
    private fun observeThemeMode() {
        lifecycleScope.launch {
            preferencesManager.darkModeFlow.collectLatest { isDarkMode ->
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
     * Actualiza el idioma de la aplicación dinámicamente.
     * @param languageCode Código del idioma (ejemplo: "en", "es").
     */
    private fun updateLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Reiniciar actividad para aplicar el nuevo idioma (opcional)
        recreate()
    }
}
