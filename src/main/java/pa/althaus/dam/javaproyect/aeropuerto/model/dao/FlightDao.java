package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import pa.althaus.dam.javaproyect.aeropuerto.app.StartupManager;
import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_FLIGHT;

public class FlightDao extends CoreDao<Flight> {

    private StartupManager startupManager;

    public FlightDao() {
        super(PATH_FLIGHT);
    }

    @Override
    protected String getCodigoFromCsvLine(String csvLine) {
        return csvLine.split(",")[0];
    }

    @Override
    protected String entidadToCSVString(Flight entidad) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        return String.format("%s,%d,%s,%s,%d,%s,%s,%s",
                entidad.getCodigoVuelo(),
                entidad.getAirlineCompany().getPrefijo(),
                entidad.getAirportOrigen().getCodigoIATA(),
                entidad.getAirportDestino().getCodigoIATA(),
                entidad.getPlazasTotales(),
                timeFormat.format(entidad.getHoraSalida()),
                timeFormat.format(entidad.getHoraLlegada()),
                entidad.getDiasOpera());
    }

    @Override
    protected Flight parseCsvLine(String csvLine) {
        String[] campos = csvLine.split(",");
        System.out.println("Campos length: " + campos.length);
        System.out.println("Campos: " + Arrays.toString(campos));

        return new Flight(
                campos[0], // Código de vuelo
                findAirlineByCode(Integer.parseInt(campos[1])), // Compañía aérea
                findAirportByCode(campos[2]), // Aeropuerto de origen
                findAirportByCode(campos[3]), // Aeropuerto de destino
                Integer.parseInt(campos[4]), // Número de plazas totales
                Time.valueOf(campos[5]), // Hora de salida
                Time.valueOf(campos[6]), // Hora de llegada
                Arrays.asList(Arrays.copyOfRange(campos, 7, campos.length)) // Días de operación
        );
    }

    public AirlineCompany findAirlineByCode(int code) {
        for (AirlineCompany airline : startupManager.getAirlineCompanies().values()) {
            if (airline.getPrefijo() == code) {
                return airline;
            }
        }
        return null;
    }

    public Airport findAirportByCode(String code) {
        return startupManager.getAirports().get(code);
    }
}
