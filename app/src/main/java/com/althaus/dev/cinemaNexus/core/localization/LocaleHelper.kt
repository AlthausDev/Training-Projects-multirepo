package com.althaus.dev.cinemaNexus.core.localization

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleHelper {

    // Cambia el idioma de la aplicación dinámicamente
    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.createConfigurationContext(config) // Usamos el contexto actualizado
        } else {
            context.resources.configuration.setLocale(locale) // En versiones anteriores
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            context
        }
    }

    // Obtiene el idioma actual
    fun getLocale(context: Context): Locale {
        return context.resources.configuration.locale
    }
}
