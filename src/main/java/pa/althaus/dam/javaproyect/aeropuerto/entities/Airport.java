package pa.althaus.dam.javaproyect.aeropuerto.entities;

/**
 *
 * @author S.Althaus
 */
public class Airport {
    private String IATACode;
    private String airportName;
    private String codigoMunicipio;

    public Airport() {
    }

    public Airport(String IATACode, String airportName, String codigoMunicipio) {
        this.IATACode = IATACode;
        this.airportName = airportName;
        this.codigoMunicipio = codigoMunicipio;
    }  
    

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
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
