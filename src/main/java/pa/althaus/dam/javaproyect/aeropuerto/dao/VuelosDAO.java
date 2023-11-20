package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Vuelos;

public interface VuelosDAO {

    void guardarVuelo(Vuelos vuelo);

    void actualizarVuelo(Vuelos vuelo);

    void eliminarVuelo(String codigoVuelo);

    Vuelos obtenerVueloPorCodigo(String codigoVuelo);

    List<Vuelos> obtenerTodosVuelos();
}
