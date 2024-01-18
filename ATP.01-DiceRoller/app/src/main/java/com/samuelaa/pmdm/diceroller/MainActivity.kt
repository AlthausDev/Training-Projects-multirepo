package com.samuelaa.pmdm.diceroller

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.samuelaa.pmdm.diceroller.databinding.ActivityMainBinding

class MainActivity(private val ViewModelFactory: ViewModelFactory) : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cara1: Int = 0
    private var cara2: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(binding.root)

        binding.btnRoll.setOnClickListener {
            val toast = Toast.makeText(this, "Dado lanzado", Toast.LENGTH_SHORT).show()
            ViewModelFactory.twoDices(6)

            //rollDice()
        }
    }

    private fun rollDice(dado1: Int, dado2: Int) {

        var imagen = R.id.imageView

        for (i in 1..2) {
            val diceImage: ImageView = findViewById(imagen)
            val drawableResource = when (dado1) {
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

//    private fun rollDice() {
//        var imagen = R.id.imageView
//
//        for (i in 1..2) {
//            val dice = Dice(6)
//            val diceRoll = dice.diceRoll()
//            val diceImage: ImageView = findViewById(imagen)
//            val drawableResource = when (diceRoll) {
//                1 -> R.drawable.dice_1
//                2 -> R.drawable.ic_launcher_foreground
//                3 -> R.drawable.dice_3
//                4 -> R.drawable.dice_4
//                5 -> R.drawable.dice_5
//                else -> R.drawable.dice_6
//            }
//            diceImage.setImageResource(drawableResource)
//            diceImage.contentDescription = diceRoll.toString()
//            imagen = R.id.imageView2
//        }
//    }
}