package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Company;

public interface CompanyDAO {

    void guardarCompany(Company company);

    void actualizarCompany(Company company);

    void eliminarCompany(String code);

    Company obtenerCompanyPorCode(String code);

    List<Company> obtenerTodasCompanies();
}
