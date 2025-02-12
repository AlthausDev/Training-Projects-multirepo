package pa.althaus.dam.javaproyect.aeropuerto.model;

/**
 * Representa un aeropuerto con detalles como el código IATA, el nombre y el código de municipio.
 * Se utiliza para gestionar información sobre aeropuertos en el sistema.
 *
 * @author S. Althaus
 */
public class Airport {
    private String codigoIATA;
    private String airportName;
    private String codigoMunicipio;

    /**
     * Constructor por defecto de la clase Airport.
     * Inicializa un objeto Airport con valores predeterminados.
     */
    public Airport() {
    }

    /**
     * Constructor que inicializa un objeto Airport con los parámetros dados.
     *
     * @param codigoIATA      Código IATA del aeropuerto. Debe seguir un formato específico.
     * @param airportName     Nombre del aeropuerto.
     * @param codigoMunicipio Código de municipio del aeropuerto.
     */
    public Airport(String codigoIATA, String airportName, String codigoMunicipio) {
        this.codigoIATA = codigoIATA;
        this.airportName = airportName;
        this.codigoMunicipio = codigoMunicipio;
    }

    // Métodos get y set

    /**
     * Obtiene el código IATA del aeropuerto.
     *
     * @return Código IATA del aeropuerto.
     */
    public String getCodigoIATA() {
        return codigoIATA;
    }

    /**
     * Establece el código IATA del aeropuerto.
     *
     * @param codigoIATA Código IATA del aeropuerto. Debe seguir un formato específico.
     */
    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    /**
     * Obtiene el nombre del aeropuerto.
     *
     * @return Nombre del aeropuerto.
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * Establece el nombre del aeropuerto.
     *
     * @param airportName Nombre del aeropuerto.
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     * Obtiene el código de municipio del aeropuerto.
     *
     * @return Código de municipio del aeropuerto.
     */
    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    /**
     * Establece el código de municipio del aeropuerto.
     *
     * @param codigoMunicipio Código de municipio del aeropuerto.
     */
    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    /**
     * Representación en formato de cadena del objeto Airport.
     *
     * @return Cadena que representa el objeto Airport.
     */
    @Override
    public String toString() {
        return "Aeropuerto" +
                "\nCódigo IATA: " + codigoIATA +
                "\nNombre del Aeropuerto: " + airportName +
                "\nCódigo de Municipio: " + codigoMunicipio;
    }
}
