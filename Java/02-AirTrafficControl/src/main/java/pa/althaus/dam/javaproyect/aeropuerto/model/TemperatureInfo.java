package pa.althaus.dam.javaproyect.aeropuerto.model;

public class TemperatureInfo {
    private String airportCode;
    private String municipalityCode;
    private double minTemperature;
    private double maxTemperature;

    public TemperatureInfo(String airportCode, String municipalityCode, double minTemperature, double maxTemperature) {
        this.airportCode = airportCode;
        this.municipalityCode = municipalityCode;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}
