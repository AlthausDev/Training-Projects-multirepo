package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_DAILYFLIGH;

public class DailyFlightDao extends CoreDao<DailyFlight> {

    public DailyFlightDao() {
        super(PATH_DAILYFLIGH);
    }

    @Override
    protected String getCodigoFromCsvLine(String csvLine) {
        return csvLine.split(",")[0];
    }

    @Override
    protected String entidadToCSVString(DailyFlight entidad) {
        return String.format("%d,%s,%s,%s,%s,%d,%f",
                entidad.getCodigoVueloDiario(),
                entidad.getCodigoVuelo(),
                entidad.getFechaVuelo(),
                entidad.getHoraSalida(),
                entidad.getHoraLlegada(),
                entidad.getPlazasOcupadas(),
                entidad.getPrecioVuelo());
    }

    @Override
    protected DailyFlight parseCsvLine(String csvLine) {
        String[] campos = csvLine.split(",");
        return new DailyFlight(
                Integer.parseInt(campos[0]), // Código Vuelo Diario
                campos[1], // Código Vuelo
                LocalDate.parse(campos[2]), // Fecha Vuelo
                LocalTime.parse(campos[3]), // Hora Salida
                LocalTime.parse(campos[4]), // Hora Llegada
                Integer.parseInt(campos[5]), // Plazas Ocupadas
                Float.parseFloat(campos[6])  // Precio Vuelo
        );
    }
    
   public HashMap<Integer, DailyFlight> obtenerVuelosDiariosPorFecha(LocalDate fecha) {
        return (HashMap<Integer, DailyFlight>) readAll().values().stream()
                .filter(vuelo -> vuelo.getFechaVuelo().equals(fecha))
                .collect(Collectors.toMap(DailyFlight::getCodigoVueloDiario, vuelo -> vuelo));
    }  

 }
