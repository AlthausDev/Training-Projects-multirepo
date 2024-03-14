import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaus.dev.project04_cartelera.data.model.Movie

class MovieDetailViewModel : ViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> get() = _movie

    private val _navigateToReservation: MutableLiveData<Boolean> = MutableLiveData()
    val navigateToReservation: LiveData<Boolean> get() = _navigateToReservation

    private val _navigateToMovieList: MutableLiveData<Boolean> = MutableLiveData()
    val navigateToMovieList: LiveData<Boolean> get() = _navigateToMovieList

    fun setMovie(movie: Movie) {
        _movie.value = movie
    }

    fun onContinueClicked() {
        _navigateToReservation.value = true
    }

    fun onCancelClicked() {
        _navigateToMovieList.value = true
    }

    fun onNavigationComplete() {
        _navigateToReservation.value = false
        _navigateToMovieList.value = false
    }
}
