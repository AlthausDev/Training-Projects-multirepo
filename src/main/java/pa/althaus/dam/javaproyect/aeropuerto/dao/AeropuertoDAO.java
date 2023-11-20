package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Aeropuerto;

public interface AeropuertoDAO {

    void guardarAeropuerto(Aeropuerto aeropuerto);

    void actualizarAeropuerto(Aeropuerto aeropuerto);

    void eliminarAeropuerto(String IATACode);

    Aeropuerto obtenerAeropuertoPorIATACode(String IATACode);

    List<Aeropuerto> obtenerTodosAeropuertos();
}
