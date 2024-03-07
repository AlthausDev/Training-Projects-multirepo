package com.althaus.dev.project04_cartelera.base

import android.app.Application
import androidx.room.Room
import com.althaus.dev.project04_cartelera.data.database.AppDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cartelera_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
