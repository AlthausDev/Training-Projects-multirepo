package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ReservationViewModel : ViewModel() {

    // LiveData para representar el estado de validación del formulario
    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> get() = _isFormValid

    // LiveData para representar el mensaje de error en caso de que el formulario sea inválido
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Función para validar el formulario de reserva
    fun validateReservationForm(movieTitle: String, numberOfTickets: Int) {
        // Verificar que el título de la película no esté vacío
        if (movieTitle.isBlank()) {
            _isFormValid.value = false
            _errorMessage.value = "Por favor, selecciona una película."
            return
        }

        // Verificar que se haya seleccionado al menos un ticket
        if (numberOfTickets <= 0) {
            _isFormValid.value = false
            _errorMessage.value = "Por favor, selecciona al menos un boleto."
            return
        }

        // Si el formulario pasa todas las validaciones, establecer el estado de validación en verdadero
        _isFormValid.value = true
    }

    // Función para enviar la reserva al servidor
    fun submitReservation(movieTitle: String, numberOfTickets: Int) {
        // Simulamos el envío de la reserva al servidor imprimiendo los datos en la consola
        println("Reserva enviada al servidor:")
        println("Película: $movieTitle")
        println("Número de boletos: $numberOfTickets")
    }
}
