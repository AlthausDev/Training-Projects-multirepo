package pa.althaus.dam.javaproyect.aeropuerto.entities;

/**
 * @author S.Althaus
 */
public class Aeropuerto {
    private String codigoIATA;
    private String airportName;
    private String codigoMunicipio;

    public Aeropuerto() {
    }

    public Aeropuerto(String codigoIATA, String airportName, String codigoMunicipio) {
        this.codigoIATA = codigoIATA;
        this.airportName = airportName;
        this.codigoMunicipio = codigoMunicipio;
    }


    public String getCodigoIATA() {
        return codigoIATA;
    }

    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }
    
    
    
}
