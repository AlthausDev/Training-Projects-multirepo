package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.util.ArrayList;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Company;

public class CompanyDAOImpl implements CompanyDAO {

    private List<Company> companies;

    public CompanyDAOImpl() {
        this.companies = new ArrayList<>();
    }

    @Override
    public void guardarCompany(Company company) {
        companies.add(company);
    }

    @Override
    public void actualizarCompany(Company company) {

    }

    @Override
    public void eliminarCompany(String code) {
        companies.removeIf(c -> c.getCode().equals(code));
    }

    @Override
    public Company obtenerCompanyPorCode(String code) {
        return companies.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Company> obtenerTodasCompanies() {
        return new ArrayList<>(companies);
    }
}
