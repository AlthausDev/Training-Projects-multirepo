package com.althaus.dev.cinemaNexus;

import android.app.Application
import com.althaus.dev.cinemaNexus.utils.LocaleHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CinemaNexusApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val currentLanguage = "en"
        val context = LocaleHelper.setLocale(applicationContext, currentLanguage)
    }
}
