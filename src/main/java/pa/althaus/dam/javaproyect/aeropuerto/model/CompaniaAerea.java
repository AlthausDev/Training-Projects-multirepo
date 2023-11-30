package pa.althaus.dam.javaproyect.aeropuerto.model;

/**
 * @author samuelaa
 */
public class CompaniaAerea {
    private int prefijo;
    private String codigo;
    private String nombre;
    private String direccion;
    private String municipio;
    private String tlfPasajero;
    private String tlfAeropuerto;

    public CompaniaAerea() {
    }

    public CompaniaAerea(int prefijo, String codigo, String nombre, String direccion, String municipio, String tlfPasajero, String tlfAeropuerto) {
        this.prefijo = prefijo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.municipio = municipio;
        this.tlfPasajero = tlfPasajero;
        this.tlfAeropuerto = tlfAeropuerto;
    }

    public int getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTlfPasajero() {
        return tlfPasajero;
    }

    public void setTlfPasajero(String tlfPasajero) {
        this.tlfPasajero = tlfPasajero;
    }

    public String getTlfAeropuerto() {
        return tlfAeropuerto;
    }

    public void setTlfAeropuerto(String tlfAeropuerto) {
        this.tlfAeropuerto = tlfAeropuerto;
    }
}
