package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pa.althaus.dam.javaproyect.aeropuerto.entities.CompaniaAerea;

public class CompaniaAereaDAOImpl implements CompaniaAereaDAO {

    private final Map<String, CompaniaAerea> companias;

    public CompaniaAereaDAOImpl() {
        this.companias = new HashMap<>();
    }

    @Override
    public void createCompaniaAerea(CompaniaAerea compania) {
        companias.put(compania.getCodigo(), compania);
    }

    @Override
    public CompaniaAerea readCompaniaAerea(String codigo) {
        return companias.get(codigo);
    }

    @Override
    public void updateCompaniaAerea(String codigo, CompaniaAerea nuevaInfo) {
        if (companias.containsKey(codigo)) {
            companias.put(codigo, nuevaInfo);
        }
    }

    @Override
    public void deleteCompaniaAerea(String codigo) {
        companias.remove(codigo);
    }

    @Override
    public CompaniaAerea obtenerCompaniaPorCodigo(String codigo) {
        return readCompaniaAerea(codigo);
    }

    @Override
    public List<CompaniaAerea> obtenerTodasCompanias() {
        return new ArrayList<>(companias.values());
    }
}
