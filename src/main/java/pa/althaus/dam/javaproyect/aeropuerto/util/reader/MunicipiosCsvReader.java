package pa.althaus.dam.javaproyect.aeropuerto.util.reader;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import static pa.althaus.dam.javaproyect.aeropuerto.util.Constants.PATH_MUNICIPIOS;

public class MunicipiosCsvReader<T> {

    public HashMap<String, String> readMunicipiosCSV() {

        String linea;
        HashMap<String, String> municipiosHashMap = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_MUNICIPIOS));
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 2) {
                    municipiosHashMap.put(datos[0], datos[1]);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return municipiosHashMap;
    }

}
