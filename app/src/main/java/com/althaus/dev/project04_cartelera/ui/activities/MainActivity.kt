package com.althaus.dev.project04_cartelera.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.althaus.dev.project04_cartelera.base.App
import com.althaus.dev.project04_cartelera.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = App.database
    }
}