package com.samuelaa.pmdm.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roll : Button = findViewById <Button> (R.id.btnRoll)
        roll.setOnClickListener {
            val toast = Toast.makeText(this, "Dado lanzado", Toast.LENGTH_SHORT).show()
            rollDice()
        }
    }

    private fun rollDice() {
        var imagen = R.id.imageView

        for (i in 1..2) {
            val dice = Dice(6)
            val diceRoll = dice.diceRoll()
            val diceImage: ImageView = findViewById(imagen)
            val drawableResource = when (diceRoll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.ic_launcher_foreground
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage.setImageResource(drawableResource)
            diceImage.contentDescription = diceRoll.toString()
            imagen = R.id.imageView2
        }
    }
}