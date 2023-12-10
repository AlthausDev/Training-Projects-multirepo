package pa.althaus.dam.javaproyect.aeropuerto.controller;

import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;

import java.time.LocalDate;
import java.util.HashMap;

public class RecaudacionesController {
    private final DailyFlightDao dailyFlightDao;

    public RecaudacionesController(DailyFlightDao dailyFlightDao) {
        this.dailyFlightDao = dailyFlightDao;
    }

    public float obtenerRecaudacionParaFecha(LocalDate fecha) {
        return (float) dailyFlightDao.obtenerVuelosDiariosPorFecha(fecha).values().stream()
                .filter(this::esVueloCompletado)
                .mapToDouble(this::calcularRecaudacionVuelo)
                .sum();
    }

    private boolean esVueloCompletado(DailyFlight dailyFlight) {
        LocalDate fechaActual = LocalDate.now();
        return dailyFlight.getFechaVuelo().isBefore(fechaActual) && dailyFlight.getPlazasOcupadas() == dailyFlight.getFlight().getPlazasTotales();
    }

    private float calcularRecaudacionVuelo(DailyFlight dailyFlight) {
        return dailyFlight.getPrecioVuelo() * dailyFlight.getPlazasOcupadas();
    }

    public static void main(String[] args) {
        // Suponiendo que ya tienes una instancia de DailyFlightDao
        DailyFlightDao dailyFlightDao = new DailyFlightDao();

        RecaudacionesController controller = new RecaudacionesController(dailyFlightDao);

        // Obtener recaudación para la fecha actual
        LocalDate fechaActual = LocalDate.now();
        float recaudacion = controller.obtenerRecaudacionParaFecha(fechaActual);

        // Imprimir resultado
        System.out.println("Recaudación para la fecha " + fechaActual + ": $" + recaudacion);
    }
}
