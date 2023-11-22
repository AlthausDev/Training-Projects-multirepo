package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Aeropuerto;

public interface AeropuertoDAO {

    void createAeropuerto(Aeropuerto aeropuerto);

    Aeropuerto readAeropuerto(String IATACode);

    void updateAeropuerto(String IATACode, Aeropuerto nuevaInfo);

    void deleteAeropuerto(String IATACode);

    List<Aeropuerto> obtenerTodosAeropuertos();
}
