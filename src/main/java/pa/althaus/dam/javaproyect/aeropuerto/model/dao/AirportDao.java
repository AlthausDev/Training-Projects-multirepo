package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_AIRPORTS;

public class AirportDao extends CoreDao<Airport> {

    public AirportDao() {
        super(PATH_AIRPORTS);
    }

    @Override
    protected String getCodigoFromCsvLine(String csvLine) {
        return csvLine.split(",")[0];
    }

    @Override
    protected String entidadToCSVString(Airport entidad) {
        return String.format("%s,%s,%s",
                entidad.getCodigoIATA(),
                entidad.getAirportName(),
                entidad.getCodigoMunicipio());
    }

    @Override
    protected Airport parseCsvLine(String csvLine) {
        String[] campos = csvLine.split(",");
        return new Airport(
                campos[0], // Código IATA
                campos[1], // Nombre del Aeropuerto
                campos[2]  // Código de Municipio
        );
    }
}
