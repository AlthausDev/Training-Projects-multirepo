import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.*;
import pa.althaus.dam.javaproyect.aeropuerto.model.*;

public class SalidasController {
    private final DailyFlightDao dailyFlightDao;

    public SalidasController(DailyFlightDao dailyFlightDao) {
        this.dailyFlightDao = dailyFlightDao;
    }

   public Map<Integer, DailyFlight> obtenerSalidas(LocalDate fecha) {
        HashMap<Integer, DailyFlight> vuelosDiarios = dailyFlightDao.obtenerVuelosDiariosPorFecha(fecha);
        vuelosDiarios.entrySet().removeIf(entry -> !entry.getValue().getFechaVuelo().equals(fecha));
        return vuelosDiarios;
    }
}
