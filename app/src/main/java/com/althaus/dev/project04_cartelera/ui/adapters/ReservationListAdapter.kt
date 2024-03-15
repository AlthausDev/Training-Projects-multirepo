package com.althaus.dev.project04_cartelera.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.databinding.ItemReservationBinding

class ReservationListAdapter : ListAdapter<Reservation, ReservationListAdapter.ReservationViewHolder>(ReservationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding = ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val currentReservation = getItem(position)
        holder.bind(currentReservation)
    }

    class ReservationViewHolder(private val binding: ItemReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.apply {
                movieTitleTextView.text = reservation.movieTitle
                numberOfTicketsTextView.text = reservation.numberOfTickets.toString()
            }
        }
    }

    private class ReservationDiffCallback : DiffUtil.ItemCallback<Reservation>() {
        override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
            return oldItem == newItem
        }
    }
}
