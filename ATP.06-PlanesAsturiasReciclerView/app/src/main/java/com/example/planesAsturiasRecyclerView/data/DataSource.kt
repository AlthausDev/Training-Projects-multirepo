package com.example.planesAsturiasRecyclerView.data

import com.example.planesAsturiasRecyclerView.model.PlanAsturias
import com.example.planesasturiasreciclerview.R

class DataSource {
    companion object {
        val listaPLanes = listOf<PlanAsturias>(
            PlanAsturias(R.drawable.cultura, "Cultura"),
            PlanAsturias(R.drawable.deportes, "Deportes\n"),
            PlanAsturias(R.drawable.gastronomia, "Gastronomia\n"),
            PlanAsturias(R.drawable.infantil, "Infantil\n"),
            PlanAsturias(R.drawable.mascotas, "Mascotas\n"),
            PlanAsturias(R.drawable.montanya, "Montaña\n"),
            PlanAsturias(R.drawable.playa, "Playa\n"),
            PlanAsturias(R.drawable.pueblos, "Pueblos\n"),
            PlanAsturias(R.drawable.restauracion, "Restauracion\n")
        )
    }
}