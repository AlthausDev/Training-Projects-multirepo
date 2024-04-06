package com.example.atp09_navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.atp09_navigation.bombilla.BombillaActivity
import com.example.atp09_navigation.databinding.ActivityMainBinding
import com.example.atp09_navigation.semaforo.SemaforoActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.btnBombilla.setOnClickListener {
            val intent = Intent(this, BombillaActivity::class.java)
            startActivity(intent)
        }

        binding.btnSemaforo.setOnClickListener {
            val intent = Intent(this, SemaforoActivity::class.java)
            startActivity(intent)
        }

    }
}