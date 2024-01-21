package com.althaus.dev.atp12_diceroller.features.diceRoll.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.althaus.dev.atp12_diceroller.databinding.ActivityMainBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel

class MainActivity : AppCompatActivity() {

    /* El this en este caso, se coloca porque se le pasa a ViewModelProvider como contexto
     * una instancia de la actividad actual, en otras palabras, el viewModel provider tiene
     * como argumento un contexto. Luego, se usa la instancia de ViewModelProvider, para obtener
     * una instancia de DiceViewModel
     *
     * this --> ViewModelProvider --> DiceViewModel
     */
    private val viewModel = ViewModelProvider(this).get(DiceViewModel::class.java)

    /* Esto se resume en:
     * Voy a usar Data Binding y necesito una instancia de ActivityMainBinding para acceder a
     * las vistas y variables definidas en el diseño XML"
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // La vista principal de la actividad es el contenedor raíz del diseño XML inflado, y
        // el contenedor raíz es el que he cascado justo arriba
        setContentView(binding.root)

        binding.btnRollDice.setOnClickListener{
            viewModel.rollDice()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}