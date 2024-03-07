package com.althaus.dev.project04_cartelera.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.althaus.dev.project04_cartelera.base.App
import com.althaus.dev.project04_cartelera.R
import com.althaus.dev.project04_cartelera.data.database.AppDatabase
import com.althaus.dev.project04_cartelera.ui.fagments.MovieListFragment

class MainActivity : AppCompatActivity() {

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-database").build()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieListFragment())
            .commit()

    }
}