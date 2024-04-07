package com.althaus.dev.atp12_diceroller.features.diceRoll.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.althaus.dev.atp12_diceroller.database.DiceRollDatabase
import com.althaus.dev.atp12_diceroller.databinding.ActivityMainBinding
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModel
import com.althaus.dev.atp12_diceroller.features.diceRoll.viewModel.DiceViewModelFactory
import com.althaus.dev.atp12_diceroller.repository.DiceRollRepository

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: DiceViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dao = DiceRollDatabase.getDatabase(application).diceRollDao()
        val repository = DiceRollRepository(dao)
        val viewModelFactory = DiceViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(this, viewModelFactory).get(DiceViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
