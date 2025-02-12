package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.app.StartupManager;
import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;

import static pa.althaus.dam.javaproyect.aeropuerto.util.Constants.PATH_FLIGHT;

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

        try {

            AirlineCompany airline = findAirlineByCode(campos[1]);
            ;
            System.out.println("Airline: " + airline);

            Airport originAirport = findAirportByCode(campos[2]);
            Airport destinationAirport = findAirportByCode(campos[3]);

            int seats = Integer.parseInt(campos[4]);
            System.out.println("Seats: " + seats);

            Time departureTime = Time.valueOf(campos[5]);
            System.out.println("Departure Time: " + departureTime);

            Time arrivalTime = Time.valueOf(campos[6]);
            System.out.println("Arrival Time: " + arrivalTime);

            List<String> days = Arrays.asList(Arrays.copyOfRange(campos, 7, campos.length));
            System.out.println("Days: " + days);

            return new Flight(
                    campos[0], // Código de vuelo
                    airline, // Compañía aérea
                    originAirport, // Aeropuerto de origen
                    destinationAirport, // Aeropuerto de destino
                    seats, // Número de plazas totales
                    departureTime, // Hora de salida
                    arrivalTime, // Hora de llegada
                    days // Días de operación
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public AirlineCompany findAirlineByCode(String code) {
        return startupManager.getAirlineCompanies().get(code);
    }

    public Airport findAirportByCode(String code) {
        return startupManager.getAirports().get(code);
    }


}
