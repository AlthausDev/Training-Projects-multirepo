package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Vuelo;

public interface VueloDAO {

    void createVuelo(Vuelo vuelo);

    Vuelo readVuelo(String codigoVuelo);

    void updateVuelo(String codigoVuelo, Vuelo nuevaInfo);

    void deleteVuelo(String codigoVuelo);

    List<Vuelo> obtenerTodosVuelos();
}
