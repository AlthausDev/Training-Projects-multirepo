package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.project04_cartelera.data.dao.ReservationDao
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
import com.althaus.dev.project04_cartelera.data.repository.impl.ReservationRepositoryImpl
import kotlinx.coroutines.launch

class ReservationListViewModel() : ViewModel() {

    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>> get() = _reservations

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private lateinit var reservationRepository: ReservationRepository
    private lateinit var reservationDao: ReservationDao

    fun initRepository(reservationRepository: ReservationRepository, reservationDao: ReservationDao) {
        this.reservationRepository = reservationRepository
        this.reservationDao = reservationDao
    }



    fun loadReservations(userId: Int) {
        viewModelScope.launch {
            try {
                val userReservations = reservationRepository.getUserReservations()
                _reservations.value = userReservations
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar las reservas: ${e.message}"
            }
        }
    }
}
