package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Aeropuerto;

public class AeropuertoDAOImpl implements AeropuertoDAO {

    private List<Aeropuerto> aeropuertos;

    public AeropuertoDAOImpl() {
        this.aeropuertos = new ArrayList<>();
    }

    @Override
    public void guardarAeropuerto(Aeropuerto aeropuerto) {
        aeropuertos.add(aeropuerto);
    }

    @Override
    public void actualizarAeropuerto(Aeropuerto aeropuerto) {

    }

    @Override
    public void eliminarAeropuerto(String IATACode) {
        aeropuertos.removeIf(a -> a.getIATACode().equals(IATACode));
    }

    @Override
    public Aeropuerto obtenerAeropuertoPorIATACode(String IATACode) {
        return aeropuertos.stream()
                .filter(a -> a.getIATACode().equals(IATACode))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Aeropuerto> obtenerTodosAeropuertos() {
        return new ArrayList<>(aeropuertos);
    }
}
