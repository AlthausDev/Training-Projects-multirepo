package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Aeropuerto;

public class AeropuertoDAOImpl implements AeropuertoDAO {

    private final Map<String, Aeropuerto> aeropuertos;

    public AeropuertoDAOImpl() {
        this.aeropuertos = new HashMap<>();
    }

    @Override
    public void createAeropuerto(Aeropuerto aeropuerto) {
        aeropuertos.put(aeropuerto.getCodigoIATA(), aeropuerto);
    }

    @Override
    public Aeropuerto readAeropuerto(String IATACode) {
        return aeropuertos.get(IATACode);
    }

    @Override
    public void updateAeropuerto(String IATACode, Aeropuerto nuevaInfo) {
        if (aeropuertos.containsKey(IATACode)) {
            aeropuertos.put(IATACode, nuevaInfo);
        }
    }

    @Override
    public void deleteAeropuerto(String IATACode) {
        aeropuertos.remove(IATACode);
    }

    @Override
    public List<Aeropuerto> obtenerTodosAeropuertos() {
        return new ArrayList<>(aeropuertos.values());
    }
}
