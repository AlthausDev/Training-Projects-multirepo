package pa.althaus.dam.javaproyect.aeropuerto.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;

public class ProximosVuelosController {
    private final DailyFlightDao dailyFlightDao;
    private static final int NUMERO_DIAS_PROXIMOS = 7;

    public ProximosVuelosController(DailyFlightDao dailyFlightDao) {
        this.dailyFlightDao = dailyFlightDao;
    }

    /**
     * Obtiene los vuelos próximos a un destino específico.
     *
     * @param destino El destino para el que se desean obtener los vuelos.
     * @return Un mapa de vuelos próximos ordenados por fecha y hora de salida.
     */
    public HashMap<Integer, DailyFlight> getVuelosProximos(String destino) {
        HashMap<Integer, DailyFlight> vuelosProximos = obtenerVuelosPrevistosPorDestino(destino);

        // Filtrar los vuelos para los siguientes siete días a la fecha actual
        vuelosProximos.entrySet().removeIf(entry ->
                entry.getValue().getFechaVuelo().isBefore(LocalDate.now())
                        || entry.getValue().getFechaVuelo().isAfter(LocalDate.now().plusDays(NUMERO_DIAS_PROXIMOS)));

        // Ordenar por fecha y hora de salida
        vuelosProximos = sortByFechaYHora(vuelosProximos);

        return vuelosProximos;
    }

    private HashMap<Integer, DailyFlight> sortByFechaYHora(HashMap<Integer, DailyFlight> vuelosProximos) {
        return vuelosProximos.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparing(DailyFlight::getFechaVuelo).thenComparing(DailyFlight::getHoraSalida)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, HashMap::new));
    }

    public HashMap<Integer, DailyFlight> obtenerVuelosPrevistosPorDestino(String destino) {
        return dailyFlightDao.readAll().values().stream()
                .filter(dailyFlight -> dailyFlight.getFlight().getAirportDestino().getCodigoIATA().equals(destino))
                .collect(Collectors.toMap(
                        DailyFlight::getCodigoVueloDiario,
                        dailyFlight -> dailyFlight,
                        (e1, e2) -> e1,
                        HashMap::new
                ));
    }
}
