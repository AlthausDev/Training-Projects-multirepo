package pa.althaus.dam.javaproyect.aeropuerto.dao;

import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;

import java.sql.ResultSet;
import java.util.HashMap;

public class AirlineCompanyDao extends CoreDao<AirlineCompany> {
    /**
     * Crea una nueva entidad en la base de datos.
     *
     * @param entidad Entidad a ser creada.
     */
    @Override
    public void create(AirlineCompany entidad) {

    }

    /**
     * Lee una entidad de la base de datos usando su código único.
     *
     * @param codigo Código único de la entidad.
     * @return La entidad recuperada.
     */
    @Override
    public AirlineCompany read(String codigo) {
        return null;
    }

    /**
     * Lee todas las entidades de la base de datos.
     *
     * @return Un mapa que asocia códigos con entidades.
     */
    @Override
    public HashMap<String, AirlineCompany> readAll() {
        return null;
    }

    /**
     * Actualiza una entidad en la base de datos.
     *
     * @param entidad Entidad a ser actualizada.
     */
    @Override
    public void update(AirlineCompany entidad) {

    }

    /**
     * Elimina una entidad de la base de datos usando su código único.
     *
     * @param codigo Código único de la entidad a ser eliminada.
     */
    @Override
    public void delete(String codigo) {

    }

    /**
     * Convierte un ResultSet a una instancia de la entidad gestionada por el DAO.
     *
     * @param rs ResultSet obtenido de una consulta a la base de datos.
     * @return Una instancia de la entidad creada a partir de los datos en el ResultSet.
     */
    @Override
    protected AirlineCompany getResultSet(ResultSet rs) {
        return null;
    }
}
