package pa.althaus.dam.javaproyect.aeropuerto.model;

/**
 * Representa una compañía aérea con detalles como el prefijo, el código, el nombre, la dirección, el municipio y los números de teléfono.
 * Se utiliza para gestionar información sobre compañías aéreas en el sistema.
 *
 * @author S. Althaus
 */
public class AirlineCompany {
    private int prefijo;
    private String codigo;
    private String nombre;
    private String direccion;
    private String municipio;
    private String tlfPasajero;
    private String tlfAeropuerto;

    /**
     * Constructor por defecto de la clase AirlineCompany.
     * Inicializa un objeto AirlineCompany con valores predeterminados.
     */
    public AirlineCompany() {
    }

    /**
     * Constructor que inicializa un objeto AirlineCompany con los parámetros dados.
     *
     * @param prefijo       Prefijo numérico entero positivo que sirve como identificador.
     * @param codigo        Código de dos caracteres que sigue un formato específico.
     * @param nombre        Nombre de la compañía aérea.
     * @param direccion     Dirección de la sede central.
     * @param municipio     Municipio de la sede central.
     * @param tlfPasajero   Teléfono de información al pasajero en formato específico.
     * @param tlfAeropuerto Teléfono de información a aeropuertos en formato específico.
     */
    public AirlineCompany(int prefijo, String codigo, String nombre, String direccion, String municipio, String tlfPasajero, String tlfAeropuerto) {
        this.prefijo = prefijo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.tlfPasajero = tlfPasajero;
        this.tlfAeropuerto = tlfAeropuerto;
    }

    // Métodos get y set

    /**
     * Obtiene el prefijo asociado al aeropuerto.
     *
     * @return Prefijo del aeropuerto.
     */
    public int getPrefijo() {
        return prefijo;
    }

    /**
     * Establece el prefijo asociado al aeropuerto.
     *
     * @param prefijo Prefijo del aeropuerto.
     */
    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * Obtiene el código IATA del aeropuerto.
     *
     * @return Código IATA del aeropuerto.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código IATA del aeropuerto.
     *
     * @param codigo Código IATA del aeropuerto. Debe seguir un formato específico.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del aeropuerto.
     *
     * @return Nombre del aeropuerto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del aeropuerto.
     *
     * @param nombre Nombre del aeropuerto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del aeropuerto.
     *
     * @return Dirección del aeropuerto.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del aeropuerto.
     *
     * @param direccion Dirección del aeropuerto.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el municipio asociado al aeropuerto.
     *
     * @return Municipio del aeropuerto.
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Establece el municipio asociado al aeropuerto.
     *
     * @param municipio Municipio del aeropuerto.
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Obtiene el número de teléfono para pasajeros del aeropuerto.
     *
     * @return Número de teléfono para pasajeros del aeropuerto.
     */
    public String getTlfPasajero() {
        return tlfPasajero;
    }

    /**
     * Establece el número de teléfono para pasajeros del aeropuerto.
     *
     * @param tlfPasajero Número de teléfono para pasajeros del aeropuerto.
     */
    public void setTlfPasajero(String tlfPasajero) {
        this.tlfPasajero = tlfPasajero;
    }

    /**
     * Obtiene el número de teléfono general del aeropuerto.
     *
     * @return Número de teléfono general del aeropuerto.
     */
    public String getTlfAeropuerto() {
        return tlfAeropuerto;
    }

    /**
     * Establece el número de teléfono general del aeropuerto.
     *
     * @param tlfAeropuerto Número de teléfono general del aeropuerto.
     */
    public void setTlfAeropuerto(String tlfAeropuerto) {
        this.tlfAeropuerto = tlfAeropuerto;
    }


    /**
     * Representación en formato de cadena del objeto AirlineCompany.
     *
     * @return Cadena que representa el objeto AirlineCompany.
     */
    @Override
    public String toString() {
        return "Compañía Aérea" +
                "\nPrefijo: " + prefijo +
                "\nCódigo: " + codigo +
                "\nNombre: " + nombre +
                "\nDirección: " + direccion +
                "\nMunicipio: " + municipio +
                "\nTeléfono del Pasajero: " + tlfPasajero +
                "\nTeléfono del Aeropuerto: " + tlfAeropuerto;
    }


}
