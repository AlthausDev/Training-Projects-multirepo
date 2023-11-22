package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.VueloDiario;

public interface VueloDiarioDAO {

    void createVueloDiario(VueloDiario vueloDiario);

    VueloDiario readVueloDiario(String codigoVuelo);

    void updateVueloDiario(String codigoVuelo, VueloDiario nuevaInfo);

    void deleteVueloDiario(String codigoVuelo);

    List<VueloDiario> obtenerTodosVuelosDiarios();
}
