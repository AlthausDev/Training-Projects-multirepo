package pa.althaus.dam.javaproyect.aeropuerto.util.reader;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import pa.althaus.dam.javaproyect.aeropuerto.model.*;

import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.*;

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
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return municipiosHashMap;
    }

    public HashMap<String, Airport> readAirportCsv() {
        HashMap<String, Airport> airportsHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_AIRPORTS))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");

                if (valores.length == 3) {
                    Airport newAirport = new Airport(valores[0], valores[1], valores[2]);
                    airportsHashMap.put(valores[0], newAirport);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return airportsHashMap;
    }

    public HashMap<String, AirlineCompany> readAirlineCsv() {
        HashMap<String, AirlineCompany> airlineHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_AIRLINECOMPANY))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");

                if (valores.length == 7) {
                    AirlineCompany newAirline = new AirlineCompany(
                            Integer.parseInt(valores[0]),
                            valores[1],
                            valores[2],
                            valores[3],
                            valores[4],
                            valores[5],
                            valores[6]);
                    airlineHashMap.put(valores[0], newAirline);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return airlineHashMap;
    }

    public HashMap<String, DailyFlight> readDailyFlightCsv() throws ParseException {
        HashMap<String, DailyFlight> dailyFlightHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_DAILYFLIGH))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(";");
                if (valores.length == 7) {
                    DailyFlight newDailyFlight = new DailyFlight(
                            Integer.parseInt(valores[0]),
                            valores[1],
                            LocalDate.parse(valores[2]),
                            LocalTime.parse(valores[3]),
                            LocalTime.parse(valores[4]),
                            Integer.parseInt(valores[5]),
                            Float.parseFloat(valores[6]));
                    dailyFlightHashMap.put(valores[0], newDailyFlight);
                }
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dailyFlightHashMap;
    }

//     public HashMap<String, Flight> readFlightCsv() {
//        HashMap<String, Flight> flightHashMap = new HashMap<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(PATH_FLIGH))) {
//            String linea;
//
//            while ((linea = br.readLine()) != null) {
//                String[] valores = linea.split(";");
//
//                if (valores.length == 8) {
//                    Flight newFlight = new Flight(
//                            valores[0]), 
//                            valores[1], 
//                            LocalDate.parse(valores[2]), 
//                            LocalTime.parse(valores[3]),
//                            LocalTime.parse(valores[4]), 
//                            Integer.parseInt(valores[5]), 
//                            Float.parseFloat(valores[6]),
//                            valores[7]);
//                    flightHashMap.put(valores[0], newFlight);
//                }
//            }
//        } catch (EOFException e) {
//            System.out.println("Fin del archivo");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return flightHashMap;
//    }
}
