package pa.althaus.dam.javaproyect.aeropuerto.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Clase base abstracta para los DAO (Data Access Object).
 *
 * @param <T> Tipo de entidad gestionada por el DAO.
 */
public abstract class CoreDao<T> {

    /**
     * Ruta del archivo CSV.
     */
    private final String csvFilePath;

    /**
     * Constructor que recibe la ruta del archivo CSV.
     *
     * @param csvFilePath Ruta del archivo CSV.
     */
    public CoreDao(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * Crea una nueva entidad en el archivo CSV.
     *
     * @param entidad Entidad a ser creada.
     */
    public void create(T entidad) {

        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.append(entidadToCSVString(entidad));
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee todas las entidades del archivo CSV.
     *
     * @return Un mapa que asocia códigos con entidades.
     */
    public HashMap<String, T> readAll() {
        HashMap<String, T> entidades = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T entidad = parseCsvLine(line);
                entidades.put(getCodigoFromCsvLine(line), entidad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entidades;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    /**
     * Obtiene el código único de una entidad desde una línea CSV.
     *
     * @param csvLine Línea del CSV.
     * @return Código único de la entidad.
     */
    protected abstract String getCodigoFromCsvLine(String csvLine);

    /**
     * Convierte una entidad a una cadena CSV.
     *
     * @param entidad Entidad a convertir.
     * @return Cadena CSV representando la entidad.
     */
    protected abstract String entidadToCSVString(T entidad);

    /**
     * Convierte una línea del CSV a una instancia de la entidad.
     *
     * @param csvLine Línea del CSV.
     * @return Instancia de la entidad creada a partir de los datos en la línea CSV.
     */
    protected abstract T parseCsvLine(String csvLine);
}
