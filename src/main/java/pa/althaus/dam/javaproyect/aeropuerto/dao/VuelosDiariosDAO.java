package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.VuelosDiarios;

public interface VuelosDiariosDAO {

    void guardarVuelosDiarios(VuelosDiarios vuelosDiarios);

    void actualizarVuelosDiarios(VuelosDiarios vuelosDiarios);

    void eliminarVuelosDiarios(String clave);

    VuelosDiarios obtenerVuelosDiariosPorClave(String clave);

    List<VuelosDiarios> obtenerTodosVuelosDiarios();
}
