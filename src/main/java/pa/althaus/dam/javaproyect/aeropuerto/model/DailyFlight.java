package pa.althaus.dam.javaproyect.aeropuerto.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Representa un vuelo diario desde y hacia un aeropuerto, con detalles sobre su ejecución.
 * Cada vuelo diario está asociado a un vuelo general registrado en el sistema.
 *
 * @author S. Althaus
 */
public class DailyFlight {

    private int codigoVueloDiario;
    private String codigoVuelo;
    private LocalDate fechaVuelo;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private int plazasOcupadas;
    private float precioVuelo;
    private Flight flight;  

    /**
     * Constructor por defecto de la clase DailyFlight.
     * Inicializa un objeto DailyFlight con valores predeterminados.
     */
    public DailyFlight() {
    }

    /**
     * Constructor que inicializa un objeto DailyFlight con los parámetros dados.
     *
     * @param codigoVueloDiario Código único para identificar un vuelo diario.
     * @param codigoVuelo       Código del vuelo asociado. Debe seguir un formato específico.
     * @param fechaVuelo        Fecha del vuelo diario. Debe ser coherente con los días que opera el vuelo.
     * @param horaSalida        Hora prevista de salida del vuelo.
     * @param horaLlegada       Hora prevista de llegada del vuelo.
     * @param plazasOcupadas    Número de plazas ocupadas en el vuelo diario.
     * @param precioVuelo       Precio medio por asiento en el vuelo diario.
     */
    public DailyFlight(int codigoVueloDiario, String codigoVuelo, LocalDate fechaVuelo, LocalTime horaSalida, LocalTime horaLlegada, int plazasOcupadas, float precioVuelo) {
        this.codigoVueloDiario = codigoVueloDiario;
        this.codigoVuelo = codigoVuelo;
        this.fechaVuelo = fechaVuelo;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.plazasOcupadas = plazasOcupadas;
        this.precioVuelo = precioVuelo;
    }

    public LocalDate getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(LocalDate fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public void setPlazasOcupadas(int plazasOcupadas) {
        this.plazasOcupadas = plazasOcupadas;
    }

    public float getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(float precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    /**
     * Obtiene el código único del vuelo diario.
     *
     * @return Código único del vuelo diario.
     */
    public int getCodigoVueloDiario() {
        return codigoVueloDiario;
    }

    /**
     * Establece el código único del vuelo diario.
     *
     * @param codigoVueloDiario Código único del vuelo diario.
     */
    public void setCodigoVueloDiario(int codigoVueloDiario) {
        this.codigoVueloDiario = codigoVueloDiario;
    }

    /**
     * Obtiene el código del vuelo asociado.
     *
     * @return Código del vuelo asociado.
     */
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    /**
     * Establece el código del vuelo asociado.
     *
     * @param codigoVuelo Código del vuelo asociado.
     */
    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }
    
        /**
     * Obtiene el vuelo asociado.
     *
     * @return Vuelo asociado.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Establece el vuelo asociado.
     *
     * @param flight Vuelo asociado.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }


    /**
     * Representación en formato de cadena del objeto DailyFlight.
     *
     * @return Cadena que representa el objeto DailyFlight.
     */
    @Override
    public String toString() {
        return "Vuelo Diario" +
                "\nCódigo de Vuelo Diario: " + codigoVueloDiario +
                "\nCódigo de Vuelo Relacionado: " + codigoVuelo +
                "\nFecha del Vuelo: " + fechaVuelo +
                "\nHora de Salida: " + horaSalida +
                "\nHora de Llegada: " + horaLlegada +
                "\nPlazas Ocupadas: " + plazasOcupadas +
                "\nPrecio del Vuelo: " + precioVuelo;
    }
}
