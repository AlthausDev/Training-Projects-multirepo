package com.example.planesAsturiasRecyclerView.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.planesAsturiasRecyclerView.adapter.PlanesRecycleAdapter
import com.example.planesAsturiasRecyclerView.data.DataSource
import com.example.planesasturiasreciclerview.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvPlanes: RecyclerView = findViewById(R.id.planesReciclyerView)
        val dataSet = DataSource.listaPLanes

        rvPlanes.adapter = PlanesRecycleAdapter(dataSet)

    }
}