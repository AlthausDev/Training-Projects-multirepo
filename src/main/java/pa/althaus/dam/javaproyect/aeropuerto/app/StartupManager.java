package pa.althaus.dam.javaproyect.aeropuerto.app;

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

public class StartupManager {

    private static final StartupManager INSTANCE = new StartupManager();
    private static HashMap<String, Airport> airports = new HashMap<>();
    private static HashMap<String, AirlineCompany> airlineCompanies = new HashMap<>();
    private static HashMap<String, Flight> flights = new HashMap<>();
    private static HashMap<String, DailyFlight> dailyFlights = new HashMap<>();
    private static HashMap<String, String> municipios = new HashMap<>();

    private StartupManager() {
         airports = new HashMap<>();
    airlineCompanies = new HashMap<>();
    flights = new HashMap<>();
    dailyFlights = new HashMap<>();
    municipios = new HashMap<>();
        init();
    }

    private void init() {       
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

    public static HashMap<String, Airport> getAirports() {
        return airports;
    }

    public static HashMap<String, AirlineCompany> getAirlineCompanies() {
        return airlineCompanies;
    }

    public static HashMap<String, Flight> getFlights() {
        return flights;
    }

    public static HashMap<String, DailyFlight> getDailyFlights() {
        return dailyFlights;
    }

    public static HashMap<String, String> getMunicipios() {
        return municipios;
    }

    protected static StartupManager getInstance() {
        return INSTANCE;
    }
}
