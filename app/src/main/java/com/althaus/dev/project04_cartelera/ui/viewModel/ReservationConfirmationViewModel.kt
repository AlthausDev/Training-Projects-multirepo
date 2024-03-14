package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
import kotlinx.coroutines.launch

class ReservationConfirmationViewModel(private val reservationRepository: ReservationRepository) : ViewModel() {

    // LiveData para representar si la reserva ha sido confirmada con éxito
    private val _reservationConfirmed = MutableLiveData<Boolean>()
    val reservationConfirmed: LiveData<Boolean> get() = _reservationConfirmed

    // LiveData para representar mensajes de error durante la confirmación de reserva
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Función para confirmar la reserva
    fun confirmReservation(reservation: Reservation) {
        viewModelScope.launch {
            try {
                // Insertar la reserva en la base de datos local utilizando Room
                reservationRepository.makeReservation(reservation)
                _reservationConfirmed.value = true
            } catch (e: Exception) {
                // Manejar errores
                _errorMessage.value = "Error al confirmar la reserva: ${e.message}"
            }
        }
    }
}
