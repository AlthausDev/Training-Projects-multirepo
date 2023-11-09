package pa.althaus.dam.javaproyect.aeropuerto.entities;

/**
 *
 * @author samuelaa
 */
public class Company {
    private int prefijo;
    private String code;
    private String compName;
    private String direccion;
    private String municipio;
    private String tlfPasajero;
    private String tlfAirport;

    public Company() {
    }

    public Company(int prefijo, String code, String compName, String direccion, String municipio, String tlfPasajero, String tlfAirport) {
        this.prefijo = prefijo;
        this.code = code;
        this.compName = compName;
        this.direccion = direccion;
        this.municipio = municipio;
        this.tlfPasajero = tlfPasajero;
        this.tlfAirport = tlfAirport;
    }

    public int getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(int prefijo) {
        this.prefijo = prefijo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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

    public String getTlfAirport() {
        return tlfAirport;
    }

    public void setTlfAirport(String tlfAirport) {
        this.tlfAirport = tlfAirport;
    }
    
    

}
