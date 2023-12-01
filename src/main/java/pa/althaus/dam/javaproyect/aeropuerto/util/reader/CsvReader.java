package pa.althaus.dam.javaproyect.aeropuerto.util.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_MUNICIPIOS;

public class CsvReader<T> {

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return municipiosHashMap;
    }

//    public HashMap<String, T> readCsv(String path) {
//        HashMap<String, T> datosHashMap = new HashMap<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String linea;
//
//            while ((linea = br.readLine()) != null) {
//                String[] valores = linea.split(";");
//
//                if (valores.length >= 2) {
//                    // Asumiendo que valores[0] es la clave y valores[1] es el valor
//                    datosHashMap.put(valores[0], (T) valores[1]);
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return datosHashMap;
//    }

}
