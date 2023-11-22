package pa.althaus.dam.javaproyect.aeropuerto.dao;

import java.io.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import pa.althaus.dam.javaproyect.aeropuerto.entities.Aeropuerto;
import pa.althaus.dam.javaproyect.aeropuerto.entities.Vuelo;

public class VueloDAOImpl implements VueloDAO {

    private static final String CSV_FILE = "vuelos.csv";

    @Override
    public void createVuelo(Vuelo vuelo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            String line = vueloToCsvLine(vuelo);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vuelo readVuelo(String codigoVuelo) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Vuelo vuelo = csvLineToVuelo(line);
                if (vuelo.getCodigoVuelo().equals(codigoVuelo)) {
                    return vuelo;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateVuelo(String codigoVuelo, Vuelo nuevaInfo) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Vuelo vuelo = csvLineToVuelo(line);
                if (vuelo.getCodigoVuelo().equals(codigoVuelo)) {
                    line = vueloToCsvLine(nuevaInfo);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVuelo(String codigoVuelo) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Vuelo vuelo = csvLineToVuelo(line);
                if (!vuelo.getCodigoVuelo().equals(codigoVuelo)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Vuelo> obtenerTodosVuelos() {
        List<Vuelo> vuelos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Vuelo vuelo = csvLineToVuelo(line);
                vuelos.add(vuelo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vuelos;
    }

    private String vueloToCsvLine(Vuelo vuelo) {
        return String.format("%s,%s,%s,%d,%s,%s,%s",
                vuelo.getCodigoVuelo(),
                vuelo.getAeropuertoOrigen().getCodigoIATA(),
                vuelo.getAeropuertoDestino().getCodigoIATA(),
                vuelo.getPlazasTotales(),
                vuelo.getHoraSalida(),
                vuelo.getHoraLlegada(),
                vuelo.getDiasOpera());
    }

    private Vuelo csvLineToVuelo(String line) {
        String[] parts = line.split(",");

        String codigoVuelo = parts[0];
        //Aeropuerto aeropuertoOrigen = obtenerAeropuertoPorCodigo(parts[1]);
        //Aeropuerto aeropuertoDestino = obtenerAeropuertoPorCodigo(parts[2]);
        int plazasTotales = Integer.parseInt(parts[3]);
        Time horaSalida = Time.valueOf(LocalTime.parse(parts[4]));
        Time horaLlegada = Time.valueOf(LocalTime.parse(parts[5]));
        String diasOpera = parts[6];

        return new Vuelo(codigoVuelo, aeropuertoOrigen, aeropuertoDestino, plazasTotales, horaSalida, horaLlegada, diasOpera);
    }

}