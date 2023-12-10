package pa.althaus.dam.javaproyect.aeropuerto.controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;

public class LlegadasController {
    private final DailyFlightDao dailyFlightDao;

    public LlegadasController(DailyFlightDao dailyFlightDao) {
        this.dailyFlightDao = dailyFlightDao;
    }

    /**
     * Obtiene las llegadas para la fecha especificada.
     *
     * @param fecha Fecha para la cual se desean obtener las llegadas.
     * @return Mapa de llegadas ordenadas por hora de llegada ascendente.
     */
    public Map<Integer, DailyFlight> obtenerLlegadas(LocalDate fecha) {
        HashMap<Integer, DailyFlight> vuelosDiarios = dailyFlightDao.obtenerVuelosDiariosPorFecha(fecha);
        vuelosDiarios.entrySet().removeIf(entry -> !entry.getValue().getFechaVuelo().equals(fecha));
        vuelosDiarios.entrySet().removeIf(entry -> entry.getValue().getHoraLlegada().isBefore(LocalTime.now()));

        return vuelosDiarios.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparing(DailyFlight::getHoraLlegada)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
