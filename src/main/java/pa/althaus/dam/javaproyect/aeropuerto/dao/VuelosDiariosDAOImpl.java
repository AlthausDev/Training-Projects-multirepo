package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.VuelosDiarios;

public class VuelosDiariosDAOImpl implements VuelosDiariosDAO {

    private HashMap<String, VuelosDiarios> vuelosDiarios;

    public VuelosDiariosDAOImpl() {
        this.vuelosDiarios = new HashMap<>();
    }

    @Override
    public void guardarVuelosDiarios(VuelosDiarios vuelosDiarios) {
        this.vuelosDiarios.put(generarClave(vuelosDiarios), vuelosDiarios);
    }

    @Override
    public void actualizarVuelosDiarios(VuelosDiarios vuelosDiarios) {

    }

    @Override
    public void eliminarVuelosDiarios(String clave) {
        this.vuelosDiarios.remove(clave);
    }

    @Override
    public VuelosDiarios obtenerVuelosDiariosPorClave(String clave) {
        return this.vuelosDiarios.get(clave);
    }

    @Override
    public List<VuelosDiarios> obtenerTodosVuelosDiarios() {
        return new ArrayList<>(this.vuelosDiarios.values());
    }

    private String generarClave(VuelosDiarios vuelosDiarios) {
        return "";
    }
}
