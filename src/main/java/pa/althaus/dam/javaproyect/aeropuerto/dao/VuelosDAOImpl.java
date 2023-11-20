package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Vuelos;

public class VuelosDAOImpl implements VuelosDAO {

    private List<Vuelos> vuelos;

    public VuelosDAOImpl() {
        this.vuelos = new ArrayList<>();
    }

    @Override
    public void guardarVuelo(Vuelos vuelo) {
        vuelos.add(vuelo);
    }

    @Override
    public void actualizarVuelo(Vuelos vuelo) {

    }

    @Override
    public void eliminarVuelo(String codigoVuelo) {
        vuelos.removeIf(v -> v.getCodigoVuelo().equals(codigoVuelo));
    }

    @Override
    public Vuelos obtenerVueloPorCodigo(String codigoVuelo) {
        return vuelos.stream()
                .filter(v -> v.getCodigoVuelo().equals(codigoVuelo))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Vuelos> obtenerTodosVuelos() {
        return new ArrayList<>(vuelos);
    }
}
