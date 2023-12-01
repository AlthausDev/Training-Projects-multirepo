package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Clase base abstracta para los DAO (Data Access Object).
 *
 * @param <T> Tipo de entidad gestionada por el DAO.
 */
public abstract class CoreDao<T> {

    /**
     * Crea una nueva entidad en la base de datos.
     *
     * @param entidad Entidad a ser creada.
     */
    public abstract void create(T entidad);

    /**
     * Lee una entidad de la base de datos usando su código único.
     *
     * @param codigo Código único de la entidad.
     * @return La entidad recuperada.
     */
    public abstract T read(String codigo);

    /**
     * Lee todas las entidades de la base de datos.
     *
     * @return Un mapa que asocia códigos con entidades.
     */
    public abstract HashMap<String, T> readAll();

    /**
     * Actualiza una entidad en la base de datos.
     *
     * @param entidad Entidad a ser actualizada.
     */
    public abstract void update(T entidad);

    /**
     * Elimina una entidad de la base de datos usando su código único.
     *
     * @param codigo Código único de la entidad a ser eliminada.
     */
    public abstract void delete(String codigo);

    /**
     * Convierte un ResultSet a una instancia de la entidad gestionada por el DAO.
     *
     * @param rs ResultSet obtenido de una consulta a la base de datos.
     * @return Una instancia de la entidad creada a partir de los datos en el ResultSet.
     */
    protected abstract T getResultSet(ResultSet rs);
}

