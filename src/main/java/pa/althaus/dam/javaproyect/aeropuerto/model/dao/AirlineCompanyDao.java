package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_AIRLINECOMPANY;

public class AirlineCompanyDao extends CoreDao<AirlineCompany> {

    public AirlineCompanyDao() {
        super(PATH_AIRLINECOMPANY);
    }

    @Override
    protected String getCodigoFromCsvLine(String csvLine) {
        return csvLine.split(",")[1];
    }

    @Override
    protected String entidadToCSVString(AirlineCompany entidad) {
        return String.format("%d,%s,%s,%s,%s,%s,%s",
                entidad.getPrefijo(),
                entidad.getCodigo(),
                entidad.getNombre(),
                entidad.getDireccion(),
                entidad.getMunicipio(),
                entidad.getTlfPasajero(),
                entidad.getTlfAeropuerto());
    }

    @Override
    protected AirlineCompany parseCsvLine(String csvLine) {
        String[] campos = csvLine.split(",");
        return new AirlineCompany(
                Integer.parseInt(campos[0]), // Prefijo
                campos[1], // Código
                campos[2], // Nombre
                campos[3], // Dirección
                campos[4], // Municipio
                campos[5], // TlfPasajero
                campos[6]  // TlfAeropuerto
        );
    }
}
