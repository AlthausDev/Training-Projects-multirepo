package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.CompaniaAerea;

public interface CompaniaAereaDAO {

    void createCompaniaAerea(CompaniaAerea compania);

    CompaniaAerea readCompaniaAerea(String codigo);

    void updateCompaniaAerea(String codigo, CompaniaAerea nuevaInfo);

    void deleteCompaniaAerea(String codigo);

    // Método para obtener una compañía por su código
    CompaniaAerea obtenerCompaniaPorCodigo(String codigo);

    // Método para obtener todas las compañías
    List<CompaniaAerea> obtenerTodasCompanias();
}
