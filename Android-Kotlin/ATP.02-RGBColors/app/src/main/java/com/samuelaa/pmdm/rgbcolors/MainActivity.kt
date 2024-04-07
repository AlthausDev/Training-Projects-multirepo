package com.samuelaa.pmdm.rgbcolors

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.samuelaa.pmdm.rgbcolors.R.id.btnColor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtColor = findViewById<TextView>(R.id.txtColor)
        val generateColor: Button = findViewById(btnColor)

        generateColor.setOnClickListener {

            val colorRojo = findViewById<TextInputEditText>(R.id.rojo).text.toString()
            val colorVerde = findViewById<TextInputEditText>(R.id.verde).text.toString()
            val colorAzul = findViewById<TextInputEditText>(R.id.azul).text.toString()

            val colorHex = "$colorRojo$colorVerde$colorAzul"
            if (crearColor(colorHex) != null) {
                txtColor.apply {
                    setBackgroundColor(Color.parseColor("#$colorHex"))
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun crearColor(color: String): String? {
        return if (color.length < 6) {
            Toast.makeText(this, "Debe indicar un valor de 2 dígitos para cada uno de los 3 canales", Toast.LENGTH_SHORT).show()
            null
        } else {
            "#$color"
        }
    }
}