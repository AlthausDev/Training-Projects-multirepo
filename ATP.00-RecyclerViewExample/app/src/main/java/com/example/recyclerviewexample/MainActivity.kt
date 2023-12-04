package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.adapter.ItemAdapter
import com.example.recyclerviewexample.adapter.ItemProvider
import com.example.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView ()
    }

    fun initRecyclerView (){
        //val recyclerView = findViewById<RecyclerView>(R.id.RecyclerExample)
        binding.RecyclerExample.layoutManager = LinearLayoutManager(this)
        binding.RecyclerExample.adapter = ItemAdapter(ItemProvider.itemList)
    }
}