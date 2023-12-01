package pa.althaus.dam.javaproyect.aeropuerto.model;

import java.sql.Time;

/**
 * Representa un vuelo con detalles como el código de vuelo, aeropuertos de origen y destino, plazas totales, horas de salida y llegada, y días de operación.
 * Se utiliza para gestionar información sobre vuelos en el sistema.
 *
 * @author S. Althaus
 */
public class Flight {
    private String codigoVuelo;
    private AirlineCompany airlineCompany;
    private Airport airportOrigen;
    private Airport airportDestino;
    private int plazasTotales;
    private Time horaSalida;
    private Time horaLlegada;
    private String diasOpera;

    /**
     * Constructor por defecto de la clase Flight.
     * Inicializa un objeto Flight con valores predeterminados.
     */
    public Flight() {
    }

    /**
     * Constructor que inicializa un objeto Flight con los parámetros dados.
     *
     * @param codigoVuelo    Código del vuelo. Debe seguir un formato específico.
     * @param airlineCompany Compañía aérea asociada al vuelo.
     * @param airportOrigen  Aeropuerto de origen del vuelo.
     * @param airportDestino Aeropuerto de destino del vuelo.
     * @param plazasTotales  Número total de plazas disponibles en el vuelo.
     * @param horaSalida     Hora prevista de salida del vuelo.
     * @param horaLlegada    Hora prevista de llegada del vuelo.
     * @param diasOpera      Días de la semana en los que opera el vuelo.
     */
    public Flight(String codigoVuelo, AirlineCompany airlineCompany, Airport airportOrigen, Airport airportDestino, int plazasTotales, Time horaSalida, Time horaLlegada, String diasOpera) {
        this.codigoVuelo = codigoVuelo;
        this.airlineCompany = airlineCompany;
        this.airportOrigen = airportOrigen;
        this.airportDestino = airportDestino;
        this.plazasTotales = plazasTotales;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.diasOpera = diasOpera;
    }

    // Métodos get y set

    /**
     * Obtiene el código del vuelo.
     *
     * @return Código del vuelo.
     */
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    /**
     * Establece el código del vuelo.
     *
     * @param codigoVuelo Código del vuelo. Debe seguir un formato específico.
     */
    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    /**
     * Obtiene la compañía aérea asociada al vuelo.
     *
     * @return Compañía aérea asociada al vuelo.
     */
    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    /**
     * Establece la compañía aérea asociada al vuelo.
     *
     * @param airlineCompany Compañía aérea asociada al vuelo.
     */
    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    /**
     * Obtiene el aeropuerto de origen del vuelo.
     *
     * @return Aeropuerto de origen del vuelo.
     */
    public Airport getAirportOrigen() {
        return airportOrigen;
    }

    /**
     * Establece el aeropuerto de origen del vuelo.
     *
     * @param airportOrigen Aeropuerto de origen del vuelo.
     */
    public void setAirportOrigen(Airport airportOrigen) {
        this.airportOrigen = airportOrigen;
    }

    /**
     * Obtiene el aeropuerto de destino del vuelo.
     *
     * @return Aeropuerto de destino del vuelo.
     */
    public Airport getAirportDestino() {
        return airportDestino;
    }

    /**
     * Establece el aeropuerto de destino del vuelo.
     *
     * @param airportDestino Aeropuerto de destino del vuelo.
     */
    public void setAirportDestino(Airport airportDestino) {
        this.airportDestino = airportDestino;
    }

    /**
     * Obtiene el número total de plazas disponibles en el vuelo.
     *
     * @return Número total de plazas disponibles.
     */
    public int getPlazasTotales() {
        return plazasTotales;
    }

    /**
     * Establece el número total de plazas disponibles en el vuelo.
     *
     * @param plazasTotales Número total de plazas disponibles.
     */
    public void setPlazasTotales(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    /**
     * Obtiene la hora de salida prevista del vuelo.
     *
     * @return Hora de salida prevista del vuelo.
     */
    public Time getHoraSalida() {
        return horaSalida;
    }

    /**
     * Establece la hora de salida prevista del vuelo.
     *
     * @param horaSalida Hora de salida prevista del vuelo.
     */
    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Obtiene la hora de llegada prevista del vuelo.
     *
     * @return Hora de llegada prevista del vuelo.
     */
    public Time getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Establece la hora de llegada prevista del vuelo.
     *
     * @param horaLlegada Hora de llegada prevista del vuelo.
     */
    public void setHoraLlegada(Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * Obtiene los días en los que opera el vuelo.
     *
     * @return Cadena que representa los días en los que opera el vuelo.
     */
    public String getDiasOpera() {
        return diasOpera;
    }

    /**
     * Establece los días en los que opera el vuelo.
     *
     * @param diasOpera Cadena que representa los días en los que opera el vuelo.
     */
    public void setDiasOpera(String diasOpera) {
        this.diasOpera = diasOpera;
    }

    /**
     * Representación en formato de cadena del objeto Flight.
     *
     * @return Cadena que representa el objeto Flight.
     */
    @Override
    public String toString() {
        return "Vuelo" +
                "\nCódigo de Vuelo: " + codigoVuelo +
                "\nCompañía Aérea: " + airlineCompany.toString() +
                "\nAeropuerto de Origen: " + airportOrigen.toString() +
                "\nAeropuerto de Destino: " + airportDestino.toString() +
                "\nPlazas Totales: " + plazasTotales +
                "\nHora de Salida: " + horaSalida +
                "\nHora de Llegada: " + horaLlegada +
                "\nDías que Opera: " + diasOpera;
    }
}
