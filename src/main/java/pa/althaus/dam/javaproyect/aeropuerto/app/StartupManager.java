package pa.althaus.dam.javaproyect.aeropuerto.app;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.nio.file.Files;
import java.nio.file.Paths;
import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.AirlineCompanyDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.AirportDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.FlightDao;
import pa.althaus.dam.javaproyect.aeropuerto.util.reader.MunicipiosCsvReader;
import pa.althaus.dam.javaproyect.aeropuerto.view.MainView;

import java.util.HashMap;
import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_AIRLINECOMPANY;
import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_AIRPORTS;
import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_DAILYFLIGHT;
import static pa.althaus.dam.javaproyect.aeropuerto.util.config.Paths.PATH_FLIGHT;

public class StartupManager {

    private static final StartupManager INSTANCE = new StartupManager();
    private static HashMap<String, Airport> airports = new HashMap<>();
    private static HashMap<String, AirlineCompany> airlineCompanies = new HashMap<>();
    private static HashMap<String, Flight> flights = new HashMap<>();
    private static HashMap<String, DailyFlight> dailyFlights = new HashMap<>();
    private static HashMap<String, String> municipios = new HashMap<>();

    private StartupManager() {
        init();
    }

    private void init() {
        generateCSVFiles();
        try {
        loadAirports();
        System.out.println("Airports loaded successfully");
        
        loadAirlineCompanies();
        System.out.println("Airline Companies loaded successfully");
        
        loadFlights();
        System.out.println("Flights loaded successfully");
        
        loadDailyFlights();
        System.out.println("Daily Flights loaded successfully");
        
        loadMunicipios();
        System.out.println("Municipios loaded successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }

        MainView mainView = new MainView();
        mainView.setVisible(true);
    }

    private void loadAirports() {
        AirportDao airportDao = new AirportDao();
        airports.putAll(airportDao.readAll());
    }

    private void loadAirlineCompanies() {
        AirlineCompanyDao airlineCompanyDao = new AirlineCompanyDao();
        airlineCompanies.putAll(airlineCompanyDao.readAll());
    }

    private void loadFlights() {
        FlightDao flightDao = new FlightDao();
        flights.putAll(flightDao.readAll());
    }

    private void loadDailyFlights() {
        DailyFlightDao dailyFlightDao = new DailyFlightDao();
        dailyFlights.putAll(dailyFlightDao.readAll());
    }

    private void loadMunicipios() {
        MunicipiosCsvReader<String> municipiosCsvReader = new MunicipiosCsvReader<>();
        municipios.putAll(municipiosCsvReader.readMunicipiosCSV());
    }

 private void generateAirportsCSV() {
    AirportDao airportDao = new AirportDao();

    // Imprimir la ruta del archivo antes de intentar crear los aeropuertos
    System.out.println("Generating airports CSV file. Path: " + airportDao.getCsvFilePath());

    airportDao.create(new Airport("IATA1", "Airport1", "MUN1"));
    airportDao.create(new Airport("IATA2", "Airport2", "MUN2"));
    airportDao.create(new Airport("IATA3", "Airport3", "MUN3"));
    airportDao.create(new Airport("IATA4", "Airport4", "MUN4"));
    airportDao.create(new Airport("IATA5", "Airport5", "MUN5"));
}


    private void generateAirlineCompaniesCSV() {
        AirlineCompanyDao airlineCompanyDao = new AirlineCompanyDao();

        airlineCompanyDao.create(new AirlineCompany(1, "AA", "American Airlines", "123 Main St", "City1", "123-456-7890", "987-654-3210"));
        airlineCompanyDao.create(new AirlineCompany(2, "BA", "British Airways", "456 Oak St", "City2", "234-567-8901", "876-543-2109"));
        airlineCompanyDao.create(new AirlineCompany(3, "LH", "Lufthansa", "789 Pine St", "City3", "345-678-9012", "765-432-1098"));
        airlineCompanyDao.create(new AirlineCompany(4, "AF", "Air France", "101 Cedar St", "City4", "456-789-0123", "654-321-0987"));
        airlineCompanyDao.create(new AirlineCompany(5, "EK", "Emirates", "202 Birch St", "City5", "567-890-1234", "543-210-9876"));
    }

    private void generateDailyFlightsCSV() {
        DailyFlightDao dailyFlightDao = new DailyFlightDao();

        dailyFlightDao.create(new DailyFlight(1, "FL1", LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(12, 0), 100, 200.0f));
        dailyFlightDao.create(new DailyFlight(2, "FL2", LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(17, 0), 80, 150.0f));
        dailyFlightDao.create(new DailyFlight(3, "FL3", LocalDate.now(), LocalTime.of(18, 0), LocalTime.of(21, 0), 120, 180.0f));
    }

    private void generateFlightsCSV() {
        FlightDao flightDao = new FlightDao();

        flightDao.create(new Flight("FL1",
                new AirlineCompany(1, "AA", "American Airlines", "123 Main St", "City1", "123-456-7890", "987-654-3210"),
                new Airport("IATA1", "Airport1", "MUN1"),
                new Airport("IATA2", "Airport2", "MUN2"),
                100, Time.valueOf("08:00:00"), Time.valueOf("12:00:00"), "Lunes, Miércoles, Viernes"));

        flightDao.create(new Flight("FL2",
                new AirlineCompany(2, "BA", "British Airways", "456 Oak St", "City2", "234-567-8901", "876-543-2109"),
                new Airport("IATA3", "Airport3", "MUN3"),
                new Airport("IATA4", "Airport4", "MUN4"),
                80, Time.valueOf("14:00:00"), Time.valueOf("18:00:00"), "Martes, Jueves, Sábado"));
    }

private void generateCSVFiles() {
    createFileIfNotExists(PATH_AIRPORTS);
    createFileIfNotExists(PATH_AIRLINECOMPANY);
    createFileIfNotExists(PATH_FLIGHT);
    createFileIfNotExists(PATH_DAILYFLIGHT);

    // Agrega impresiones para verificar las rutas de los archivos
    System.out.println("PATH_AIRPORTS: " + PATH_AIRPORTS);
    System.out.println("PATH_AIRLINECOMPANY: " + PATH_AIRLINECOMPANY);
    System.out.println("PATH_FLIGHT: " + PATH_FLIGHT);
    System.out.println("PATH_DAILYFLIGHT: " + PATH_DAILYFLIGHT);

    generateAirportsCSV();
    generateAirlineCompaniesCSV();
    generateFlightsCSV();
    generateDailyFlightsCSV();
}

// ...

private void createFileIfNotExists(String filePath) {
    // Verificar si el archivo existe
    if (!Files.exists(Paths.get(filePath))) {
        // Si no existe, intentar crear el directorio y el archivo
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            Files.createFile(Paths.get(filePath));
            System.out.println("File created: " + filePath);  // Agrega impresión
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    protected static StartupManager getInstance() {
        return INSTANCE;
    }
}
