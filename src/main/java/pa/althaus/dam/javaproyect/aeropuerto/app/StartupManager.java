package pa.althaus.dam.javaproyect.aeropuerto.app;

import pa.althaus.dam.javaproyect.aeropuerto.model.AirlineCompany;
import pa.althaus.dam.javaproyect.aeropuerto.model.Airport;
import pa.althaus.dam.javaproyect.aeropuerto.model.DailyFlight;
import pa.althaus.dam.javaproyect.aeropuerto.model.Flight;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.AirlineCompanyDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.AirportDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.DailyFlightDao;
import pa.althaus.dam.javaproyect.aeropuerto.model.dao.FlightDao;
import pa.althaus.dam.javaproyect.aeropuerto.util.reader.MunicipiosCsvReader;
import pa.althaus.dam.javaproyect.aeropuerto.view.MainView;

import java.util.HashMap;

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
        // Cargar datos desde archivos CSV usando DAOs
        loadAirports();
        loadAirlineCompanies();
        loadFlights();
        loadDailyFlights();
        loadMunicipios();

        // Iniciar la interfaz de usuario
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

    protected static StartupManager getInstance() {
        return INSTANCE;
    }
}
