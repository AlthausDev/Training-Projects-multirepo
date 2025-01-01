package com.althaus.dev.cinemaNexus

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

        // Observamos el idioma para cambiar la configuración sin recrear la Activity
        observeLanguageChange()

        // Cargamos la UI Compose
        setContent {
            // En Compose controlamos el tema oscuro
            MainScreen(preferencesManager)
        }
    }

    /**
     * Observa el flujo de idioma desde PreferencesManager y actualiza la configuración.
     * (Si prefieres, podrías mover esta lógica a Compose para 100% "composear" el idioma,
     *  pero típicamente se maneja aquí por la necesidad de updateConfiguration).
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
     */
    private fun updateLocale(languageCode: String) {
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

    /**
     * Ejemplo de función expuesta si quieres cambiar el idioma manualmente.
     */
    suspend fun onLanguageSelected(languageCode: String) {
        preferencesManager.saveLanguage(languageCode)
        // updateLocale(languageCode) // Aplicación inmediata sin recreate()
        // recreate() // Solo si quisieras un reinicio completo
    }
}

/**
 * MainScreen composable que observa `darkModeFlow` y aplica
 * el tema Compose sin AppCompatDelegate.setDefaultNightMode(...).
 */
@Composable
fun MainScreen(preferencesManager: PreferencesManager) {
    // Observamos el booleano isDarkMode mediante collectAsState
    val isDarkMode by preferencesManager.darkModeFlow.collectAsState(initial = false)

    // Podrías observar languageFlow aquí también y manejarlo en Compose,
    // pero para strings.xml y resources, típicamente se hace en la Activity.
    // val languageCode by preferencesManager.languageFlow.collectAsState(initial = "es")
    // LaunchedEffect(languageCode) { /* ...update locale si quisieras hacerlo aquí... */ }

    // Pasamos isDarkMode a CinemaNexusTheme en lugar de setDefaultNightMode
    CinemaNexusTheme(darkTheme = isDarkMode) {
        NavigationGraph()
    }
}
