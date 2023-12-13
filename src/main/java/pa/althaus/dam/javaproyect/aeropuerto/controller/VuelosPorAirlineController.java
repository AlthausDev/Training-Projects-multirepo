
package pa.althaus.dam.javaproyect.aeropuerto.controller;

import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.FlightDao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class VuelosPorAirlineController {
    private final DailyFlightDao dailyFlightDao;
    private final FlightDao flightDao;

    public VuelosPorAirlineController(DailyFlightDao dailyFlightDao, FlightDao flightDao) {
        this.dailyFlightDao = dailyFlightDao;
        this.flightDao = flightDao;
    }

    public HashMap<String, Flight> obtenerVuelosPorCompaniaEnFecha(String compania, LocalDate fecha) {
        if (fecha == null) {
        fecha = LocalDate.now();
        }
        return dailyFlightDao.obtenerVuelosDiariosPorFecha(fecha).values().stream()
                .filter(dailyFlight -> dailyFlight.getFlight().getAirlineCompany().getNombre().equals(compania))
                .collect(Collectors.toMap(
                        dailyFlight -> dailyFlight.getFlight().getCodigoVuelo(),
                        DailyFlight::getFlight,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static void main(String[] args) {
        // Suponiendo que ya tienes instancias de DailyFlightDao y FlightDao
        DailyFlightDao dailyFlightDao = new DailyFlightDao();
        FlightDao flightDao = new FlightDao();

        VuelosPorAirlineController controller = new VuelosPorAirlineController(dailyFlightDao, flightDao);

        // Obtener vuelos por compañía en la fecha actual
        LocalDate fechaActual = LocalDate.now();
        HashMap<String, Flight> vuelosEnFechaActual = controller.obtenerVuelosPorCompaniaEnFecha("NombreCompania", fechaActual);

        // Imprimir resultados
        vuelosEnFechaActual.forEach((codigoVuelo, flight) -> {
            System.out.println("Código de Vuelo: " + codigoVuelo);
            System.out.println(flight.toString());
            System.out.println("--------");
        });
    }
}
