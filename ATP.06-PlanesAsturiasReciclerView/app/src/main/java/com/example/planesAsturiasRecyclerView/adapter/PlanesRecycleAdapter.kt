package com.example.planesAsturiasRecyclerView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planesAsturiasRecyclerView.model.PlanAsturias
import com.example.planesasturiasreciclerview.R


class PlanesRecycleAdapter(val planesDataSet: List<PlanAsturias>) :
    RecyclerView.Adapter<PlanesRecycleAdapter.PlanesViewHolder>() {

    class PlanesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPlan: ImageView = itemView.findViewById(R.id.imagePlan)
        val txtSlogan: TextView = itemView.findViewById(R.id.txtSlogan)

        fun vincularModeloVista(item: PlanAsturias) {
            imgPlan.setImageResource(item.ImgResourseId)
            txtSlogan.text = item.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanesViewHolder {
        val inflador = LayoutInflater.from(parent.context)
        val vistaInflada: View = inflador.inflate(R.layout.item_plan_asturias, parent, false)
        return PlanesViewHolder(vistaInflada)
    }

    override fun onBindViewHolder(holder: PlanesViewHolder, position: Int) {
        holder.vincularModeloVista(planesDataSet[position])
    }

    override fun getItemCount() = planesDataSet.size


}

