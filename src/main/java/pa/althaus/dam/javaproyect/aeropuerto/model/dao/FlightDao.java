package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;

import java.sql.Time;
import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_FLIGH;

public class FlightDao extends CoreDao<Flight> {

    public FlightDao() {
        super(PATH_FLIGH);
    }

    @Override
    protected String getCodigoFromCsvLine(String csvLine) {
        return csvLine.split(",")[0];
    }

    @Override
    protected String entidadToCSVString(Flight entidad) {
        return String.format("%s,%s,%s,%s,%s,%s,%d,%s,%s",
                entidad.getCodigoVuelo(),
                entidad.getAirlineCompany().getPrefijo(),
                entidad.getAirlineCompany().getCodigo(),
                entidad.getAirlineCompany().getNombre(),
                entidad.getAirlineCompany().getDireccion(),
                entidad.getAirlineCompany().getMunicipio(),
                entidad.getAirlineCompany().getTlfPasajero(),
                entidad.getAirlineCompany().getTlfAeropuerto(),
                entidad.getPlazasTotales(),
                entidad.getHoraSalida().toString(),
                entidad.getHoraLlegada().toString(),
                entidad.getDiasOpera());
    }

    @Override
    protected Flight parseCsvLine(String csvLine) {
        String[] campos = csvLine.split(",");
        return new Flight(
                campos[0], // Código de vuelo
                new AirlineCompany(
                        Integer.parseInt(campos[1]), // Prefijo
                        campos[2], // Código
                        campos[3], // Nombre
                        campos[4], // Dirección
                        campos[5], // Municipio
                        campos[6], // TlfPasajero
                        campos[7] // TlfAeropuerto
                ),
                new Airport(campos[8], campos[9], campos[10]), // Ajusta según tu estructura
                new Airport(campos[11], campos[12], campos[13]), // Ajusta según tu estructura
                Integer.parseInt(campos[14]), // Número de plazas totales
                Time.valueOf(campos[15]), // Hora de salida
                Time.valueOf(campos[16]), // Hora de llegada
                campos[17] // Días de operación
        );
    }   
   
}
