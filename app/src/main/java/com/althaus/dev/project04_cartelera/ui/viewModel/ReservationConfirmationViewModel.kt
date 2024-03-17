import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.project04_cartelera.data.dao.ReservationDao
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
import kotlinx.coroutines.launch

class ReservationConfirmationViewModel : ViewModel() {

    private val _reservationConfirmed = MutableLiveData<Boolean>()
    val reservationConfirmed: LiveData<Boolean> get() = _reservationConfirmed

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private lateinit var reservationRepository: ReservationRepository
    private lateinit var reservationDao: ReservationDao

    fun initRepository(reservationRepository: ReservationRepository, reservationDao: ReservationDao) {
        this.reservationRepository = reservationRepository
        this.reservationDao = reservationDao
    }

    fun confirmReservationAndSaveToDatabase(reservation: Reservation) {
        viewModelScope.launch {
            try {
                _reservationConfirmed.value = true
                saveReservationToDatabase(reservation)
            } catch (e: Exception) {
                _errorMessage.value = "Error al confirmar la reserva: ${e.message}"
            }
        }
    }

    private suspend fun saveReservationToDatabase(reservation: Reservation) {
        val totalPrice = calculateTotalPrice(reservation.numberOfTickets)
        val reservationToSave = Reservation(
            movieTitle = reservation.movieTitle,
            numberOfTickets = reservation.numberOfTickets,
            totalPrice = totalPrice
        )
        reservationRepository.makeReservation(reservationToSave)
    }

    private fun calculateTotalPrice(numberOfTickets: Int): Double {
        val basePrice = 7.0 // Precio base por entrada
        return numberOfTickets * basePrice
    }
}
